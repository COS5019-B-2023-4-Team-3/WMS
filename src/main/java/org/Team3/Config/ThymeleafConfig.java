package org.Team3.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

/**
 * ThymeleafConfig class provides configurations for integrating Thymeleaf
 * with Spring Security in the application.
 *
 * Thymeleaf is a server-side Java template engine for web and standalone
 * environments.
 * Spring Security Dialect is an extension to Thymeleaf, which provides
 * integration with Spring Security to handle security-related tasks in Thymeleaf
 * templates.
 *
 * This class defines a Spring bean for SpringSecurityDialect, which enables the
 * usage of Spring Security features in Thymeleaf templates.
 */
@Configuration
public class ThymeleafConfig {

    /**
     * Returns a SpringSecurityDialect bean for integrating Thymeleaf with Spring Security.
     *
     * @return SpringSecurityDialect bean for enabling Spring Security features in Thymeleaf templates.
     */
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
}

