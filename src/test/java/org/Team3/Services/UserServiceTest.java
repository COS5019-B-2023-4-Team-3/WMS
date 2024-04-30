package org.Team3.Services;

import org.Team3.Entities.User;
import org.Team3.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class UserServiceTest {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        //we need to mock the UserRepository and PasswordEncoder
        // as we don't want to interact with the actual database or the password encoder during unit testing
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService = new UserService(userRepository);
        ReflectionTestUtils.setField(userService, "passwordEncoder", passwordEncoder);
    }

    @Test
    public void getAllUsersTest() {
        User user1 = new User();
        User user2 = new User();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserByIdTest() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void createUserTest() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setId(1L);
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUser(1L, user);

        assertNotNull(updatedUser);
        assertEquals(1L, updatedUser.getId());
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void deleteUserTest() {
        when(userRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = userService.deleteUser(1L);

        assertTrue(isDeleted);
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void authenticateUserTest() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("encodedPassword");
        when(userRepository.findByUsername("test")).thenReturn(user);
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        boolean isAuthenticated = userService.authenticateUser("test", "password");

        assertTrue(isAuthenticated);
        verify(userRepository, times(1)).findByUsername("test");
        verify(passwordEncoder, times(1)).matches("password", "encodedPassword");
    }

    @Test
    public void userExistsTest() {
        when(userRepository.existsByUsername("test")).thenReturn(true);

        boolean exists = userService.userExists("test");

        assertTrue(exists);
        verify(userRepository, times(1)).existsByUsername("test");
    }

    @Test
    public void registerUserTest() {
        when(userRepository.existsByUsername("test")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        boolean isRegistered = userService.registerUser("test", "password");

        assertTrue(isRegistered);
        verify(userRepository, times(1)).existsByUsername("test");
        verify(passwordEncoder, times(1)).encode("password");
    }
}