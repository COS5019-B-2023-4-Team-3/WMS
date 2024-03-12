package org.Team3.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlertController {

    @GetMapping("/alerts")
    public String showPage(){
        return "alerts";
    }
}
