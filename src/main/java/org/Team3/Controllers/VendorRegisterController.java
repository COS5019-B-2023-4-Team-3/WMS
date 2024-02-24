package org.Team3.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VendorRegisterController {

    @GetMapping("/vendor-register")
    public String showSignupForm() {
        return "vendor-register";
    }
}
