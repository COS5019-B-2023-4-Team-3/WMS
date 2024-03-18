package org.Team3.Services;

class RoleServiceTest {

//    @Mock
//    private RoleRepository roleRepository;
//
//    @InjectMocks
//    private RoleService roleService;
//    private final Role externalRole = new Role();
//    private final Role employeeRole = new Role();
//    private final Role adminRole = new Role();
//
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        externalRole.setId(1L);
//        externalRole.setName("ROLE_EXTERNAL");
//        employeeRole.setId(2L);
//        employeeRole.setName("ROLE_EMPLOYEE");
//        adminRole.setId(3L);
//        adminRole.setName("ROLE_ADMIN");
//    }
//
//    @Test
//    void testGetAllRoles() {
//        // Mock data
//        List<Role> roles = new ArrayList<>();
//        roles.add(externalRole);
//        roles.add(employeeRole);
//        roles.add(adminRole);
//
//        when(roleRepository.findAll()).thenReturn(roles);
//
//        // Call the service method
//        List<Role> result = roleService.getAllRoles();
//
//        // Assertions
//        assertEquals(3, result.size());
//        assertEquals("ROLE_EXTERNAL", result.get(0).getName());
//        assertEquals("ROLE_EMPLOYEE", result.get(1).getName());
//        assertEquals("ROLE_ADMIN", result.get(2).getName());
//    }
//
//    @Test
//    void testGetRoleById() {
//        when(roleRepository.findById(1L)).thenReturn(Optional.of(employeeRole));
//
//        // Call the service method
//        Role result = roleService.getRoleById(1L);
//
//        // Assertions
//        assertNotNull(result);
//        assertEquals("ROLE_EMPLOYEE", result.getName());
//    }
//
//    @Test
//    void testCreateRole() {
//        // Mock data
//        Role role = new Role("ROLE_EMPLOYEE");
//
//        when(roleRepository.save(role)).thenReturn(role);
//
//        // Call the service method
//        Role result = roleService.createRole(role);
//
//        // Assertions
//        assertNotNull(result);
//        assertEquals("ROLE_EMPLOYEE", result.getName());
//    }
//
//    @Test
//    void testUpdateRole() {
//        // Mock data
//        Role existingRole = employeeRole;
//        Role updatedRole = adminRole;
//
//        when(roleRepository.existsById(1L)).thenReturn(true);
//        when(roleRepository.save(updatedRole)).thenReturn(updatedRole);
//
//        // Call the service method
//        Role result = roleService.updateRole(1L, updatedRole);
//
//        // Assertions
//        assertNotNull(result);
//        assertEquals("ROLE_ADMIN", result.getName());
//    }
//
//    @Test
//    void testDeleteRole() {
//        // Mock data
//        long roleId = 1L;
//
//        when(roleRepository.existsById(roleId)).thenReturn(true);
//
//        // Call the service method
//        boolean result = roleService.deleteRole(roleId);
//
//        // Assertions
//        assertTrue(result);
//        verify(roleRepository, times(1)).deleteById(roleId);
//    }
}