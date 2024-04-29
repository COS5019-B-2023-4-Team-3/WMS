package org.Team3.Config;

import org.Team3.Services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.servlet.http.HttpServletRequest;


/**
 * SecurityConfig class provides configurations for securing the application
 * using Spring Security. This class extends WebSecurityConfigurerAdapter
 * to customize security settings.
 *
 * Customize these configurations based on the application's requirements and
 * security policies. You can also add additional features such as password hashing,
 * session management, CSRF protection, etc., as needed.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Configures the authentication manager to use the custom user details service
     * and password encoder.
     *
     * @param auth AuthenticationManagerBuilder object to configure authentication.
     * @throws Exception if an error occurs while configuring authentication.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passWordEncoder());
    }

    /**
     * Configures HTTP security settings such as URL access, login, logout, and CSRF protection.
     *
     * @param http HttpSecurity object to configure HTTP security.
     * @throws Exception if an error occurs while configuring HTTP security.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/register").permitAll() // Allow access to the custom registration page
                .antMatchers("/vendor-register").permitAll()
                .antMatchers("/error").permitAll() // Permit access to the error page
                .antMatchers("/homepage").authenticated() // Require authentication for accessing the homepage
//                .antMatchers("/users/**").hasRole("ADMIN") // Require ADMIN role to access /users
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/login")
                .failureUrl("/login?error=invalid_username_or_password")
                .successForwardUrl("/homepage") // Redirect to the homepage after successful login
                .permitAll() // Allow access to the custom login page
                .and()
                .logout().permitAll()
                .logoutUrl("/logout") // Configure the logout URL
                .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable();

        //@TODO: Configure session management
//        http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .invalidSessionUrl("/login")
//                .sessionFixation().migrateSession()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true);
    }

    /**
     * Returns a PasswordEncoder bean for encoding passwords using PBKDF2 algorithm.
     *
     * @return PasswordEncoder bean for encoding passwords.
     */
    @Bean
    public PasswordEncoder passWordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    /**
     * Determines the error parameter from the request.
     *
     * @param request HttpServletRequest object representing the HTTP request.
     * @return String representing the error parameter from the request.
     */
    private String determineError(HttpServletRequest request) {
        return request.getParameter("error");
    }
}

