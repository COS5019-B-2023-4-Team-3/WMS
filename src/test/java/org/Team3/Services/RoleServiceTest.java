package org.Team3.Services;

import org.Team3.Entities.Role;
import org.Team3.Repositories.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleServiceTest {
    private RoleRepository roleRepository;
    private RoleService roleService;

    @BeforeEach
    public void setUp() {
        roleRepository = Mockito.mock(RoleRepository.class);
        roleService = new RoleService(roleRepository);
    }

    @Test
    public void getAllRolesTest() {
        Role role1 = new Role();
        Role role2 = new Role();
        when(roleRepository.findAll()).thenReturn(Arrays.asList(role1, role2));

        List<Role> roles = roleService.getAllRoles();

        assertEquals(2, roles.size());
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    public void getRoleByIdTest() {
        Role role = new Role();
        role.setId(1L);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Role foundRole = roleService.getRoleById(1L);

        assertNotNull(foundRole);
        assertEquals(1L, foundRole.getId());
        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    public void createRoleTest() {
        Role role = new Role();
        when(roleRepository.save(role)).thenReturn(role);

        Role createdRole = roleService.createRole(role);

        assertNotNull(createdRole);
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    public void updateRoleTest() {
        Role role = new Role();
        role.setId(1L);
        when(roleRepository.existsById(1L)).thenReturn(true);
        when(roleRepository.save(role)).thenReturn(role);

        Role updatedRole = roleService.updateRole(1L, role);

        assertNotNull(updatedRole);
        assertEquals(1L, updatedRole.getId());
        verify(roleRepository, times(1)).existsById(1L);
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    public void deleteRoleTest() {
        when(roleRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = roleService.deleteRole(1L);

        assertTrue(isDeleted);
        verify(roleRepository, times(1)).existsById(1L);
        verify(roleRepository, times(1)).deleteById(1L);
    }
}