package org.Team3.Services;

import org.Team3.Entities.Role;
import org.Team3.Entities.User;
import org.Team3.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * CustomUserDetailsService is a service class that implements the UserDetailsService interface provided by Spring Security.
 * It is responsible for loading user-specific data during the authentication process.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads user-specific data by username during the authentication process.
     * @param username The username provided during login
     * @return UserDetails object representing the authenticated user
     * @throws UsernameNotFoundException If the user with the given username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user from the repository based on the provided username
        User user = userRepository.findByUsername(username);
        // Throw exception if user is not found
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        // Create UserDetails object with user details and authorities
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername()) // Set username
                .password(user.getPassword()) // Set password
                .authorities(getAuthorities(user.getRole())) // Set user authorities based on role
                .build();
    }

    /**
     * Retrieves authorities (roles) for a user based on the associated role object.
     * @param role The role associated with the user
     * @return GrantedAuthority object representing the user's role
     */
    private GrantedAuthority getAuthorities(Role role) {
        return new SimpleGrantedAuthority(role.getName()); // Create and return a SimpleGrantedAuthority object for the role
    }
}
