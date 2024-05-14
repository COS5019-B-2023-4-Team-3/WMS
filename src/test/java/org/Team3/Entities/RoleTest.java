package org.Team3.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class RoleTest {
    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role("ROLE_USER");
        role.setId(1L);
    }

    @Test
    void testRoleProperties() {
        Role newRole = new Role("ROLE_ADMIN");
        assertEquals("ROLE_ADMIN", newRole.getName());
        assertNull(newRole.getId());

        assertEquals(1L, role.getId());
        role.setId(2L);
        assertEquals(2L, role.getId());

        assertEquals("ROLE_USER", role.getName());
        role.setName("ROLE_MANAGER");
        assertEquals("ROLE_MANAGER", role.getName());

        String expected = "Role{id=2, name='ROLE_MANAGER'}";
        assertEquals(expected, role.toString());
    }

}