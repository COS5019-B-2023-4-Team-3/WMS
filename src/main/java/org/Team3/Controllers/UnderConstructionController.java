package org.Team3.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * UnderConstructionController class handles requests related to displaying an "Under Construction" page.
 *
 * This controller provides a method to show a page indicating that the requested functionality or page
 * is under construction or not yet implemented.
 */
@Controller
public class UnderConstructionController {

    /**
     * Displays the "Under Construction" page.
     *
     * @return String representing the logical view name of the "Under Construction" page.
     */
    @GetMapping("/under-construction")
    public String showUnderConstructionPage() {
        return "under-construction";
    }
}