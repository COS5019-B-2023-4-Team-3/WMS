package org.Team3.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RoleTest {
    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role("ROLE_USER");
        role.setId(1L);
    }

    @Test
    void testConstructorWithName() {
        Role newRole = new Role("ROLE_ADMIN");
        assertEquals("ROLE_ADMIN", newRole.getName());
        assertNull(newRole.getId());
    }

    @Test
    void testGetId() {
        assertEquals(1L, role.getId());
    }

    @Test
    void testSetId() {
        role.setId(2L);
        assertEquals(2L, role.getId());
    }

    @Test
    void testGetName() {
        assertEquals("ROLE_USER", role.getName());
    }

    @Test
    void testSetName() {
        role.setName("ROLE_MANAGER");
        assertEquals("ROLE_MANAGER", role.getName());
    }

    @Test
    void testToString() {
        String expected = "Role{id=1, name='ROLE_USER'}";
        assertEquals(expected, role.toString());
    }

}