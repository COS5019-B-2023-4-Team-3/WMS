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

/**
 * HomeController class handles requests related to the application's home page.
 *
 * This controller provides methods to display the homepage, handle POST requests
 * related to the homepage, and handle user logout.
 */
@Controller
public class HomeController {

    /**
     * Displays the homepage and adds the user's role to the model.
     *
     * @param model Model object to add attributes for rendering the view.
     * @param principal Principal object representing the currently authenticated user.
     * @return String representing the logical view name of the homepage.
     */
    @GetMapping("/homepage")
    public String showHomepage(Model model, Principal principal) {
        String role = getRoleForUser(principal);
        model.addAttribute("role", role);
        return "homepage";
    }

    /**
     * Handles POST requests related to the homepage.
     *
     * @return String representing the redirection URL to the homepage.
     */
    @PostMapping("/homepage")
    public String handlePostRequest() {
        return "redirect:/homepage";
    }

    /**
     * Retrieves the role of the authenticated user.
     *
     * @param principal Principal object representing the currently authenticated user.
     * @return String representing the role of the user.
     */
    private String getRoleForUser(Principal principal) {
        if (principal instanceof Authentication authentication) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                String role = authority.getAuthority();
                if ("ROLE_ADMIN".equals(role)) {
                    return "ADMIN";
                } else if ("ROLE_EMPLOYEE".equals(role)) {
                    return "EMPLOYEE";
                } else if ("ROLE_EXTERNAL".equals(role)) {
                    return "EXTERNAL";
                }
            }
        }
        // If no specific role is found, return a default role (e.g., "UNKNOWN")
        return "UNKNOWN";
    }

    /**
     * Handles user logout by invalidating the authentication session.
     *
     * @param request HttpServletRequest object representing the HTTP request.
     * @param response HttpServletResponse object representing the HTTP response.
     * @param model Model object to add attributes for rendering the view.
     * @return String representing the redirection URL to the login page with a logout parameter.
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout"; // Redirect to the login page with logout parameter
    }
}
