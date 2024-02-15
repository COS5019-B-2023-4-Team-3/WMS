package org.Team3.Controllers;

import org.Team3.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * contains an endpoint /reports/product-inventory
 * that triggers the generation of the product inventory report when accessed.
 */
@Controller
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/product-inventory")
    public ResponseEntity<String> generateProductInventoryReport(){
        try{
            reportService.generateProductInventoryReport("product-inventory.xlsx");
            return new ResponseEntity<>("Product inventory report generated successfully", HttpStatus.OK);
        }catch (IOException ex){
            ex.printStackTrace();
            return new ResponseEntity<>("Failed to generate product inventory report", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
