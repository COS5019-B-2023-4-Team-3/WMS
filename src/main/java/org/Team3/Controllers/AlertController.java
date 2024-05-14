package org.Team3.Controllers;

import org.Team3.Entities.Alert;
import org.Team3.Entities.Product;
import org.Team3.Services.AlertService;
import org.Team3.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * The AlertController class is responsible for handling requests related to alerts.
 * It is annotated with @Controller to indicate that it is a Spring MVC controller component.
 */
@Controller
public class AlertController {
    @Autowired
    private AlertService alertservice;

    /**
     * Handles GET requests to "/alerts" endpoint and returns the name of the view template "alerts".
     * @return The name of the view template "alerts"
     */
    @GetMapping("/alerts")
    public String showPage(Model model){
        List<Alert> alerts = alertservice.getAllAlerts();
        model.addAttribute("alerts", alerts);
        return "alerts";

    }
}
