package org.Team3.Controllers;

import org.Team3.Entities.User;
import org.Team3.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private Validator validator;

    private ValidatorFactory factory;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        user = new User();
    }

    @Test
    void getAllUsers_ReturnsListOfUsers() {
        List<User> userList = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(userList);

        assertEquals(userList, userController.getAllUsers());
    }

    @Test
    void getUserById_WithValidId_ReturnsUser() {
        Long id = 1L;
        when(userService.getUserById(id)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(id);

        assertEquals(user, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getUserById_WithInvalidId_ReturnsNotFound() {
        Long id = 1L;
        when(userService.getUserById(id)).thenReturn(null);

        ResponseEntity<User> response = userController.getUserById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createUser_WithValidUser_ReturnsCreatedUser() {
        // Mock the behavior of userService.createUser(user) method
        when(userService.createUser(user)).thenReturn(user);

        // Call the controller method
        ResponseEntity<User> response = userController.createUser(user);

        // Assertions
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void updateUser_WithValidUser_ReturnsUpdatedUser() {
        // Prepare test data
        Long id = 1L;
        User updatedUser = new User();
        updatedUser.setId(id);

        // Mock the behavior of userService.updateUser(user) method
        when(userService.updateUser(id, updatedUser)).thenReturn(updatedUser);

        // Call the controller method
        ResponseEntity<User> response = userController.updateUser(id, updatedUser);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    void deleteUser_WithValidId_ReturnsNoContent() {
        Long id = 1L;

        when(userService.deleteUser(id)).thenReturn(true);

        ResponseEntity<Void> response = userController.deleteUser(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}