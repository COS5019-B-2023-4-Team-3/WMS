package org.Team3.Controllers;

import com.itextpdf.text.DocumentException;
import org.Team3.Services.PDFGeneratorService;
import org.Team3.Services.ReportService;
import org.Team3.Services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ReportController class defines endpoints to handle report generation.
 *
 * This controller is responsible for processing HTTP requests related to report generation
 * displaying reports, and handling report exports.
 */
@Controller

public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private PDFGeneratorService pdfGeneratorService;

    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * RegisterController class defines endpoints to handle user registration.
     *
     * This controller is responsible for processing HTTP requests related to user registration,
     * displaying the registration form, and handling user registration submissions.
     */
    @GetMapping("/reports")
    public String showPage(@RequestParam(required = false) String filter, Model model, HttpServletRequest request) {
        endDate = LocalDate.now();

        showLineChart("week", model, request);
        return "reports";
    }

    /**
     * Handles GET requests to "/line-chart" and shows the line chart.
     *
     * @param filter The filter to apply to the line chart.
     * @param model The model to add attributes to.
     * @param request The HTTP request.
     * @return The name of the view to render.
     */
    @GetMapping("/line-chart")
    public String showLineChart(@RequestParam(required = false) String filter, Model model, HttpServletRequest request){
        endDate = LocalDate.now();

        switch (filter) {
            case "week":
            default:
                startDate = endDate.minusWeeks(1);
                break;
            case "month":
                startDate = endDate.minusMonths(1);
                break;
            case "year":
                startDate = endDate.minusYears(1);
                break;
        }
        model.addAttribute("salesData", saleService.getSalesInRange(startDate, endDate));

        String referer = request.getHeader("Referer");
        if(referer != null){
            if (referer.contains("/homepage")) {
                return "homepage";
            }
        }
        //default view
        return "reports";
    }

    /**
     * Retrieves sales data in a given date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of maps containing the sales data.
     */
    private List<Map<String, Object>> getSalesInRange(LocalDate startDate, LocalDate endDate) {
        return saleService.getSalesInRange(startDate, endDate);
    }

    /**
     * Handles GET requests to "/generate-pdf" and generating a PDF report.
     *
     * @param response The HTTP response.
     * @throws IOException If an I/O error occurs.
     */
    @GetMapping("generate-pdf")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("report/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        try {
            this.pdfGeneratorService.export(response, getSalesInRange(startDate, endDate));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}

