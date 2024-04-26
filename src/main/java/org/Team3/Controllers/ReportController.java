package org.Team3.Controllers;

import org.Team3.Entities.Sale;
import org.Team3.Services.ReportService;
import org.Team3.Services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * The ReportController class contains endpoints for generating reports.
 */
@Controller

public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    public SaleService saleService;

    /**
     * Displays the reports page.
     *
     * @return The name of the reports page template
     */
    @GetMapping("/reports")
    public String showPage(Model model) {
        List<Map<String, Object>> salesInRange = saleService.getLastWeekSales();

        System.out.println(salesInRange);

        model.addAttribute("salesData", salesInRange);
        return "reports";
    }


}

