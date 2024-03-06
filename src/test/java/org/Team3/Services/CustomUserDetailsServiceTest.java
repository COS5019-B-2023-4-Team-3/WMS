package org.Team3.Services;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomUserDetailsServiceTest {

//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private CustomUserDetailsService userDetailsService;
//
//    private User mockUser;
//
//    private Role mockAdmin;
//    @BeforeEach
//    public void setup(){
//        Role mockEmployee = new Role();
//        mockEmployee.setName("ROLE_EMPLOYEE");
//
//        mockAdmin = new Role();
//        mockAdmin.setName("ROLE_ADMIN");
//
//        mockUser = new User();
//
//        mockUser.setUsername("testUser");
//        mockUser.setPassword("password");
//        mockUser.setRole(mockEmployee);
//    }
//
//    @Test
//    void testLoadUserByUsername_UserFound() {
//        // Mock user retrieval from repository
//
//        when(userRepository.findByUsername("testUser")).thenReturn(mockUser);
//
//        // Call the loadUserByUsername method
//        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");
//
//        // Assert that the UserDetails object is not null
//        assertNotNull(userDetails);
//        // Assert that the username matches the mock user's username
//        assertEquals("testUser", userDetails.getUsername());
//        // Assert that the user has the correct authority
//        assertTrue(userDetails.getAuthorities().stream()
//                .anyMatch(authority -> authority.getAuthority().equals("ROLE_EMPLOYEE")));
//    }
//
//
//    @Test
//    void testLoadUserByUsername_UserNotFound() {
//        // Mock user retrieval from repository (return null)
//        when(userRepository.findByUsername("nonExistentUser")).thenReturn(null);
//
//        // Call the loadUserByUsername method and expect a UsernameNotFoundException
//        assertThrows(UsernameNotFoundException.class,
//                () -> userDetailsService.loadUserByUsername("nonExistentUser"));
//    }
//
//    @Test
//    void testLoadUserByUsername_UserAuthorities() {
//        // Mock user retrieval from repository with a specific role
//        mockUser.setUsername("adminUser");
//        mockUser.setPassword("password");
//        mockUser.setRole(mockAdmin);
//        when(userRepository.findByUsername("adminUser")).thenReturn(mockUser);
//
//        // Call the loadUserByUsername method
//        UserDetails userDetails = userDetailsService.loadUserByUsername("adminUser");
//
//        // Assert that the user has the correct authority based on the mock role
//        assertEquals("ROLE_ADMIN", userDetails.getAuthorities().iterator().next().getAuthority());
//    }
}