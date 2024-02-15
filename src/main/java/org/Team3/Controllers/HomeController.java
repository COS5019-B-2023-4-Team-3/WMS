package org.Team3.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/homepage")
    public String showHomepage(){
        return "homepage";
    }

    @PostMapping("/homepage")
    public String handlePostRequest() {
        return "redirect:/homepage";
    }
}
