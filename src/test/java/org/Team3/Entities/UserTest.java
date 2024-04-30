package org.Team3.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    private User user;

    @BeforeEach
    void setUp(){
        user = new User();
    }

    @Test
    void testUserProperties() {
        user.setUsername("testUser");
        assertEquals("testUser", user.getUsername());

        user.setPassword("testPassword");
        assertEquals("testPassword", user.getPassword());

        assertFalse(user.isAccountNonExpired());
        assertFalse(user.isAccountNonLocked());
        assertFalse(user.isCredentialsNonExpired());
        assertFalse(user.isEnabled());

        Role role = new Role();
        role.setName("ADMIN");
        user.setRole(role);
        assertEquals(1, user.getAuthorities().size());
        assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));

        Role userRole = new Role();
        userRole.setName("USER");
        user.setRole(userRole);
        assertEquals(userRole, user.getRole());
    }
}