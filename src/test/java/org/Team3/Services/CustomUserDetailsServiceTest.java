package org.Team3.Services;
import org.Team3.Entities.Role;
import org.Team3.Entities.User;
import org.Team3.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomUserDetailsServiceTest {
    private UserRepository userRepository;
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        customUserDetailsService = new CustomUserDetailsService(userRepository);
    }

    @DisplayName("Load User By Username When User Exists")
    @Test
    public void loadUserByUsernameUserExists() {
        User user = new User();
        user.setUsername("test_admin");
        user.setPassword("test");
        Role role = new Role();
        role.setName("ADMIN");
        user.setRole(role);
        when(userRepository.findByUsername("test_admin")).thenReturn(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("test_admin");

        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN")));
    }

    @DisplayName("Load User By Username When User Does Not Exist")
    @Test
    public void loadUserByUsernameUserDoesNotExist() {
        when(userRepository.findByUsername("testUser")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("testUser"));
    }

    @DisplayName("Load User By Username When Username Is Null")
    @Test
    public void loadUserByUsernameWhenUsernameIsNull() {
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername(null));
    }
}