package org.Team3.Controllers;

import org.Team3.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    private PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();

    @GetMapping("/register")
    public String showSignupForm() {
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@RequestParam(name = "error", required = false) String error, @RequestParam String email, @RequestParam String password, Model model) {
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
