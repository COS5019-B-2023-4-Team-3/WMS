package org.Team3.Controllers;

import org.Team3.Entities.Sale;
import org.Team3.Services.PDFGeneratorService;
import org.Team3.Services.ReportService;
import org.Team3.Services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private SaleService saleService;

    @Autowired
    private PDFGeneratorService pdfGeneratorService;

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
    @GetMapping("generate-pdf")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("report/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response);
    }
}

