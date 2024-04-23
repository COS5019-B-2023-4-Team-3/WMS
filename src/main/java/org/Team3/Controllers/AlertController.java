package org.Team3.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The AlertController class is responsible for handling requests related to alerts.
 * It is annotated with @Controller to indicate that it is a Spring MVC controller component.
 */
@Controller
public class AlertController {

    /**
     * Handles GET requests to "/alerts" endpoint and returns the name of the view template "alerts".
     * @return The name of the view template "alerts"
     */
    @GetMapping("/alerts")
    public String showPage(){
        return "alerts";
    }
}
