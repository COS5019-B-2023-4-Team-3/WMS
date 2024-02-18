package org.Team3.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/homepage")
    public String showHomepage(Model model, Principal principal) {
        String role = getRoleForUser(principal);
        model.addAttribute("role", role);
        return "homepage";
    }

    @PostMapping("/homepage")
    public String handlePostRequest() {
        return "redirect:/homepage";
    }

    private String getRoleForUser(Principal principal) {
        if (principal instanceof Authentication authentication) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if ("Admin".equals(authority.getAuthority())) {
                    return "Admin";
                }
            }
        }
        return "Employee";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout"; // Redirect to the login page with logout parameter
    }
}