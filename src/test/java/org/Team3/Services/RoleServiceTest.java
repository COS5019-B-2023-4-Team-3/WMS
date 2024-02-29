package org.Team3.Services;

import org.Team3.Entities.Role;
import org.Team3.Repositories.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;
    private Role externalRole = new Role();
    private Role employeeRole = new Role();
    private Role adminRole = new Role();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        externalRole.setId(1l);
        externalRole.setName("ROLE_EXTERNAL");
        employeeRole.setId(2l);
        employeeRole.setName("ROLE_EMPLOYEE");
        adminRole.setId(3l);
        adminRole.setName("ROLE_ADMIN");
    }

    @Test
    void testGetAllRoles() {
        // Mock data
        List<Role> roles = new ArrayList<>();
        roles.add(employeeRole);
        roles.add(adminRole);

        when(roleRepository.findAll()).thenReturn(roles);

        // Call the service method
        List<Role> result = roleService.getAllRoles();

        // Assertions
        assertEquals(2, result.size());
        assertEquals("ROLE_EMPLOYEE", result.get(0).getName());
        assertEquals("ROLE_ADMIN", result.get(1).getName());
    }

    @Test
    void testGetRoleById() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(employeeRole));

        // Call the service method
        Role result = roleService.getRoleById(1L);

        // Assertions
        assertNotNull(result);
        assertEquals("ROLE_EMPLOYEE", result.getName());
    }

    @Test
    void testCreateRole() {
        // Mock data
        Role role = new Role("ROLE_EMPLOYEE");

        when(roleRepository.save(role)).thenReturn(role);

        // Call the service method
        Role result = roleService.createRole(role);

        // Assertions
        assertNotNull(result);
        assertEquals("ROLE_EMPLOYEE", result.getName());
    }

    @Test
    void testUpdateRole() {
        // Mock data
        Role existingRole = employeeRole;
        Role updatedRole = adminRole;

        when(roleRepository.existsById(1L)).thenReturn(true);
        when(roleRepository.save(updatedRole)).thenReturn(updatedRole);

        // Call the service method
        Role result = roleService.updateRole(1L, updatedRole);

        // Assertions
        assertNotNull(result);
        assertEquals("ROLE_ADMIN", result.getName());
    }

    @Test
    void testDeleteRole() {
        // Mock data
        long roleId = 1L;

        when(roleRepository.existsById(roleId)).thenReturn(true);

        // Call the service method
        boolean result = roleService.deleteRole(roleId);

        // Assertions
        assertTrue(result);
        verify(roleRepository, times(1)).deleteById(roleId);
    }
}