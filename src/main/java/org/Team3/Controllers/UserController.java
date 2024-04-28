package org.Team3.Controllers;
import org.Team3.Entities.Role;
import org.Team3.Entities.User;
import org.Team3.Services.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * defines endpoints to handle CRUD operations
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public String showPage(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }


    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable ("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user, Model model) {
        User existingUser = userService.getUserById(id);
        existingUser.setId(id);
        existingUser.setUsername(user.getUsername());

        //Check the role name
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

        userService.updateUser(existingUser);
        return "redirect:/users";
    }
}
