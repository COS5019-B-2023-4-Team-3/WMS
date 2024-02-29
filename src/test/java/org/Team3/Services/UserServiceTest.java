package org.Team3.Services;

import org.Team3.Entities.User;
import org.Team3.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    private User testUser1, testUser2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser1 = new User();
        testUser1.setId(1L);
        testUser1.setUsername("User1");
        testUser1.setPassword("password");

        testUser2 = new User();
        testUser1.setId(2L);
        testUser1.setUsername("User2");
        testUser1.setPassword("password");

    }

    @Test
    void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        // Add sample users to the list

        userList.add(testUser1);
        userList.add(testUser2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
    }

    @Test
    void testGetUserById() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser1));

        User result = userService.getUserById(1L);
        assertEquals(testUser1, result);
    }

    @Test
    void testCreateUser() {
        // Mock the userRepository to return testUser1 when save is called with any User object
        when(userRepository.save(any(User.class))).thenReturn(testUser1);

        // Call the createUser method with testUser1 and store the result
        User result = userService.createUser(testUser1);

        // Assert that the result has the same attributes as testUser1
        assertEquals(testUser1.getUsername(), result.getUsername());
        assertEquals(testUser1.getPassword(), result.getPassword());
    }

    @Test
    void testUpdateUser() {
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(testUser1);

        User result = userService.updateUser(1L, testUser1);
        assertEquals(testUser1, result);
    }

    @Test
    void testDeleteUser() {
        when(userRepository.existsById(1L)).thenReturn(true);

        assertTrue(userService.deleteUser(1L));
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testAuthenticateUser() {
        // Mock the userRepository to return the testUser1 when findByUsername is called with "user1"
        when(userRepository.findByUsername("user1")).thenReturn(testUser1);

        // Mock the passwordEncoder to return true when matches is called with "password" and the encoded password
        when(passwordEncoder.matches("password", testUser1.getPassword())).thenReturn(true);

        // Test the authenticateUser method with the correct username and password
        assertTrue(userService.authenticateUser("user1", "password"));
    }


    @Test
    void testUserExists() {
        when(userRepository.existsByUsername("user1")).thenReturn(true);

        assertTrue(userService.userExists("user1"));
    }

    @Test
    void testRegisterUser() {
        when(userRepository.existsByUsername("user1")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        assertTrue(userService.registerUser("user1", "password"));
        verify(userRepository, times(1)).save(any(User.class));
    }
}