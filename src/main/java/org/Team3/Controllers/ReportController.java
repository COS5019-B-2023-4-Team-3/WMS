package org.Team3.Controllers;

import org.Team3.Entities.Sale;
import org.Team3.Services.ReportService;
import org.Team3.Services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

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
        //get specified range of sales
        //add as a model attribute
        //use model to access data in html
        List<Sale> salesInRange = saleService.getAllSales();
        Model salesData = model.addAttribute("weekSaleData");
        return "reports";
    }


}
/**
     * Endpoint for generating the product inventory report.
     * @return ResponseEntity with a success message and HTTP status OK if the report is generated successfully,
     *         or an error message and HTTP status INTERNAL_SERVER_ERROR if there's an error during report generation
     */
//    @GetMapping("/product-inventory")
//    public ResponseEntity<String> generateProductInventoryReport() {
//        try {
//            reportService.generateProductInventoryReport("product-inventory.xlsx");
//            return new ResponseEntity<>("Product inventory report generated successfully", HttpStatus.OK);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return new ResponseEntity<>("Failed to generate product inventory report", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

