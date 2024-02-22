package org.Team3.Controllers;

import org.Team3.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error != null) {
            String errorMessage = switch (error) {
                case "invalid_username_or_password" -> "Invalid username or password.";
                case "unauthorized" -> "You are not authorized to access this page.";
                default -> "An unknown error occurred.";
            };
            model.addAttribute("error", errorMessage);
            System.out.println(errorMessage);
        }
        return "login";
    }

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
