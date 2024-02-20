package org.Team3.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp(){
        user = new User();
    }

    @Test
    void getUsername_ShouldReturnCorrectUsername() {
        String username = "testUser";
        user.setUsername(username);

        String result = user.getUsername();

        assertEquals(username, result);
    }

    @Test
    void getPassword_ShouldReturnCorrectPassword() {
        String password = "testPassword";
        user.setPassword(password);

        String result = user.getPassword();

        assertEquals(password, result);
    }

    @Test
    void isAccountNonExpired_ShouldReturnFalse() {
        assertFalse(user.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked_ShouldReturnFalse() {
        assertFalse(user.isAccountNonLocked());
    }


    @Test
    void isCredentialsNonExpired_ShouldReturnFalse() {
        assertFalse(user.isCredentialsNonExpired());
    }

    @Test
    void isEnabled_ShouldReturnFalse() {
        assertFalse(user.isEnabled());
    }

    @Test
    void getAuthorities_ShouldReturnCorrectRole() {
        // Arrange
        Role role = new Role();
        role.setRoleName("ADMIN");
        user.setRole(role);

        // Act
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // Assert
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    void getRole_ShouldReturnCorrectRole() {
        Role role = new Role();
        role.setRoleName("USER");
        user.setRole(role);

        assertEquals(role, user.getRole());
    }
}