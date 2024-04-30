package org.Team3.Services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;

@SpringBootTest
public class PDFGeneratorServiceTest {
    private PDFGeneratorService pdfGeneratorService;

    @BeforeEach
    public void setUp() {
        pdfGeneratorService = Mockito.mock(PDFGeneratorService.class);
    }

    @DisplayName("Export Returns Correct Response Status")
    @Test
    public void exportReturnsCorrectResponseStatus() throws Exception {
        MockHttpServletResponse response = new MockHttpServletResponse();
        List<Map<String, Object>> salesData = List.of(new HashMap<>());

        doAnswer(invocation -> {
            HttpServletResponse responseArg = invocation.getArgument(0);
            responseArg.setStatus(200);
            return null;
        }).when(pdfGeneratorService).export(eq(response), eq(salesData));

        pdfGeneratorService.export(response, salesData);

        assertEquals(200, response.getStatus());
    }

    @DisplayName("Export Returns Correct Content Type")
    @Test
    public void exportReturnsCorrectContentType() throws Exception {
        MockHttpServletResponse response = new MockHttpServletResponse();
        List<Map<String, Object>> salesData = List.of(new HashMap<>());

        doAnswer(invocation -> {
            HttpServletResponse responseArg = invocation.getArgument(0);
            responseArg.setContentType("application/pdf");
            return null;
        }).when(pdfGeneratorService).export(eq(response), eq(salesData));

        pdfGeneratorService.export(response, salesData);

        assertEquals("application/pdf", response.getContentType());
    }

    @DisplayName("Export Returns Non-Empty Content")
    @Test
    public void exportReturnsNonEmptyContent() throws Exception {
        MockHttpServletResponse response = new MockHttpServletResponse();
        List<Map<String, Object>> salesData = List.of(new HashMap<>());

        doAnswer(invocation -> {
            HttpServletResponse responseArg = invocation.getArgument(0);
            responseArg.getWriter().write("Test content");
            return null;
        }).when(pdfGeneratorService).export(eq(response), eq(salesData));

        pdfGeneratorService.export(response, salesData);

        assertTrue(response.getContentAsByteArray().length > 0);
    }
}