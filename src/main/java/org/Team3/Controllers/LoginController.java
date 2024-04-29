package org.Team3.Controllers;

import org.Team3.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * LoginController class handles requests related to user authentication and login.
 *
 * This controller provides methods to display the login form, process login requests,
 * and handle authentication errors.
 */
@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Displays the login form and handles authentication errors, if any.
     *
     * @param model Model object to add attributes for rendering the view.
     * @param error String representing the error message (if any).
     * @return String representing the logical view name of the login form.
     */
    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error != null) {
            String errorMessage = switch (error) {
                case "invalid_username_or_password" -> "Invalid username or password.";
                case "unauthorized" -> "You are not authorized to access this page.";
                default -> "An unknown error occurred.";
            };
            model.addAttribute("error", errorMessage);
        }
        return "login";
    }

    /**
     * Processes login requests and redirects users based on authentication status.
     *
     * @param error String representing the error message (if any).
     * @param username String representing the username provided by the user.
     * @param password String representing the password provided by the user.
     * @param model Model object to add attributes for rendering the view.
     * @return String representing the redirection URL based on the authentication status.
     */
    @PostMapping("/login")
    public String loginUser(@RequestParam(name = "error", required = false) String error,
                            @RequestParam String username, @RequestParam String password, Model model) {
        if (!userService.userExists(username) || !userService.authenticateUser(username, password)) {
            model.addAttribute("error", "invalid_username_or_password");
            return "redirect:/login?error=invalid_username_or_password";
        }
        return "redirect:/homepage";
    }
}
