package org.Team3.Services;

import org.Team3.Entities.Role;
import org.Team3.Entities.User;
import org.Team3.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    private User mockUser;

    private Role mockEmployee;
    private Role mockExternal;
    private Role mockAdmin;
    @BeforeEach
    public void setup(){
        mockEmployee = new Role();
        mockEmployee.setName("ROLE_EMPLOYEE");

        mockExternal = new Role();
        mockExternal.setName("ROLE_EXTERNAL");

        mockAdmin = new Role();
        mockAdmin.setName("ROLE_ADMIN");

        mockUser = new User();

        mockUser.setUsername("testUser");
        mockUser.setPassword("password");
        mockUser.setRole(mockEmployee);
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        // Mock user retrieval from repository

        when(userRepository.findByUsername("testUser")).thenReturn(mockUser);

        // Call the loadUserByUsername method
        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

        // Assert that the UserDetails object is not null
        assertNotNull(userDetails);
        // Assert that the username matches the mock user's username
        assertEquals("testUser", userDetails.getUsername());
        // Assert that the user has the correct authority
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_EMPLOYEE")));
    }


    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Mock user retrieval from repository (return null)
        when(userRepository.findByUsername("nonExistentUser")).thenReturn(null);

        // Call the loadUserByUsername method and expect a UsernameNotFoundException
        assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("nonExistentUser"));
    }

    @Test
    void testLoadUserByUsername_UserAuthorities() {
        // Mock user retrieval from repository with a specific role
        mockUser.setUsername("adminUser");
        mockUser.setPassword("password");
        mockUser.setRole(mockAdmin);
        when(userRepository.findByUsername("adminUser")).thenReturn(mockUser);

        // Call the loadUserByUsername method
        UserDetails userDetails = userDetailsService.loadUserByUsername("adminUser");

        // Assert that the user has the correct authority based on the mock role
        assertEquals("ROLE_ADMIN", userDetails.getAuthorities().iterator().next().getAuthority());
    }
}