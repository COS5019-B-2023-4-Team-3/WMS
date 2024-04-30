package org.Team3.Controllers;
import org.Team3.Services.PDFGeneratorService;
import org.Team3.Services.ReportService;
import org.Team3.Services.SaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReportControllerTest {

    @InjectMocks
    private ReportController reportController;

    @Mock
    private ReportService reportService;

    @Mock
    private SaleService saleService;

    @Mock
    private PDFGeneratorService pdfGeneratorService;

    @Mock
    private Model model;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void showPageReturnsReportsView() {
        String viewName = reportController.showPage(null, model, request);
        verify(saleService).getSalesInRange(any(LocalDate.class), any(LocalDate.class));
        assertEquals("reports", viewName);
    }

    @Test
    public void generatePDFCallsExport() throws Exception {
        List<Map<String, Object>> salesData = List.of();
        when(saleService.getSalesInRange(any(LocalDate.class), any(LocalDate.class))).thenReturn(salesData);
        reportController.generatePDF(response);
        verify(pdfGeneratorService).export(response, salesData);
    }
}