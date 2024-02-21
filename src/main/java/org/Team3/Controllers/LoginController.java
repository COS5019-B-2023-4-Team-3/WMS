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
            if (error.equals("invalid_username_or_password")) {
                model.addAttribute("error", "Invalid username or password.");
            } else if (error.equals("unauthorized")) {
                model.addAttribute("error", "You are not authorized to access this page.");
            } else {
                model.addAttribute("error", "An unknown error occurred.");
            }
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam(name = "error", required = false) String error, @RequestParam String username, @RequestParam String password, Model model){
        // Log the entry of the method
        System.out.println("Entering loginUser method");

        // Log the value of the error parameter
        System.out.println("Error parameter value: " + error);

        // Check if the user already exists in the database
        if (!userService.userExists(username)) {
            // Log user existence check
            System.out.println("User does not exist in the database");

            // User already exists, add error message to redirect attributes
            model.addAttribute("error", "Username does not exist");
            return "redirect:/login?error=true";
        }


        if(!userService.authenticateUser(username, password)){
            // Log authentication failure
            System.out.println("Authentication failed");

            model.addAttribute("error", "Username or password incorrect");
            return "redirect:/login?error=true";
        }

        // Log successful login
        System.out.println("Successful login");
        return "redirect:/homepage";
    }
}
