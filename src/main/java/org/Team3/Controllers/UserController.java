package org.Team3.Controllers;
import org.Team3.Entities.Role;
import org.Team3.Entities.User;
import org.Team3.Services.UserService;

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
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showPage(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

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
     * Handles GET requests to "/users/edit/{id}" and shows the form to edit a user.
     *
     * @param id The ID of the user to edit.
     * @param model The model to add the user to.
     * @return The name of the view to render, in this case "user-edit".
     */
    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable ("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-edit";
    }

    /**
     * Handles POST requests to "/users/{id}" and updates a user.
     *
     * @param id The ID of the user to update.
     * @param user The user object containing the updated user details. This object is bound to the form that was submitted.
     * @return A redirect to the "/users" page after the user has been updated.
     */
    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        User existingUser = userService.getUserById(id);
        existingUser.setId(id);
        existingUser.setUsername(user.getUsername());

        // Check the role name
        // update the id accordingly

        Role role = new Role();

        switch (user.getRole().getName()) {
            case "ADMIN":
                role.setName("ADMIN");
                role.setId(3L);
                break;
            case "EMPLOYEE":
                role.setName("EMPLOYEE");
                role.setId(2L);
                break;
            case "EXTERNAL":
                role.setName("EXTERNAL");
                role.setId(1L);
                break;
            default:
                break;
        }
        existingUser.setRole(role);

        userService.updateUser(id, existingUser);
        return "redirect:/users";
    }

    /**
     * Deletes a user by ID.
     * DELETE request to "/users/{id}"
     * @param id The ID of the user to delete
     * @return ResponseEntity<Void> with status 204 if user successfully deleted, or ResponseEntity with status 404 if user not found
     */
    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}