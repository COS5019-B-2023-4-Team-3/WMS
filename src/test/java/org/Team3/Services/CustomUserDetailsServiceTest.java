package org.Team3.Services;
import org.Team3.Entities.Role;
import org.Team3.Entities.User;
import org.Team3.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
public class CustomUserDetailsServiceTest {
    private UserRepository userRepository;
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        customUserDetailsService = new CustomUserDetailsService();
        ReflectionTestUtils.setField(customUserDetailsService, "userRepository", userRepository);
    }

    @Test
    public void testLoadUserByUsernameUserExists() {
        // Arrange
        User user = new User();
        user.setUsername("test_admin");
        user.setPassword("test");
        Role role = new Role();
        role.setName("ADMIN");
        user.setRole(role);
        when(userRepository.findByUsername("test_admin")).thenReturn(user);

        // Act
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("test_admin");

        // Assert
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN")));
    }

    @Test
    public void testLoadUserByUsernameUserDoesNotExist() {
        // Arrange
        when(userRepository.findByUsername("testUser")).thenReturn(null);

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("testUser"));
    }

}