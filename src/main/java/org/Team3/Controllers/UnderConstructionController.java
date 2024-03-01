package org.Team3.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UnderConstructionController {

    @GetMapping("/under-construction")
    public String showUnderConstructionPage() {
        return "under-construction"; // this should match the name of your HTML file
    }
}
