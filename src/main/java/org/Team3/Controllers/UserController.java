package org.Team3.Controllers;
import org.Team3.Entities.User;
import org.Team3.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * UserController class defines endpoints to handle CRUD operations related to users.
 * It serves as a controller in the MVC architecture.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService; // Autowired UserService for user-related operations

    /**
     * Retrieves a user by ID.
     * GET request to "/users/{id}"
     * @param id The ID of the user to retrieve
     * @return ResponseEntity<User> containing the user if found, or ResponseEntity with status 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Displays a list of users.
     * GET request to "/users"
     * @param model The model to add the list of users to
     * @return The view name "users" to display the list of users
     */
    @GetMapping
    public String showUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    /**
     * Creates a new user.
     * POST request to "/users"
     * @param user The user object to be created, validated using @Valid annotation
     * @return ResponseEntity<User> containing the created user with status 201 if successful
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * Updates an existing user.
     * PUT request to "/users/{id}"
     * @param id The ID of the user to update
     * @param user The updated user object, validated using @Valid annotation
     * @return ResponseEntity<User> containing the updated user if successful, or ResponseEntity with status 404 if user not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Deletes a user by ID.
     * DELETE request to "/users/{id}"
     * @param id The ID of the user to delete
     * @return ResponseEntity<Void> with status 204 if user successfully deleted, or ResponseEntity with status 404 if user not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}