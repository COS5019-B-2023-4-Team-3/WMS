package org.Team3.Controllers;

import org.Team3.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * RegisterController class defines endpoints to handle user registration.
 *
 * This controller is responsible for processing HTTP requests related to user registration,
 * displaying the registration form, and handling user registration submissions.
 */
@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the user registration form.
     *
     * @return String representing the view name for the registration form.
     */
    @GetMapping("/register")
    public String showSignupForm() {
        return "register";
    }

    /**
     * Registers a new user based on the provided email and password.
     *
     * @param error  String representing any error occurred during registration (optional).
     * @param email  String representing the email of the user to be registered.
     * @param password String representing the password of the user to be registered.
     * @param model  Model object for adding attributes to be used in the view.
     * @return String representing the redirection URL.
     *         If registration is successful, redirects to the login page.
     *         If registration fails due to an existing username, redirects back to the registration form
     *         with an error message indicating that the username already exists.
     */
    @PostMapping("/register")
    public String registerUser(@RequestParam(name = "error", required = false) String error,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        // Check if the user already exists in the database
        if (userService.userExists(email)) {
            // User already exists, add error message to redirect attributes
            model.addAttribute("error", "Username already exists");
            return "redirect:/register?error=username_already_exists";
        }

        // Register the new user
        userService.registerUser(email, password);
        // User registered successfully, redirect to the login page
        return "redirect:/login";
    }
}