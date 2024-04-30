package org.Team3.Controllers;
import org.Team3.Entities.Alert;
import org.Team3.Services.AlertService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.testng.Assert.assertEquals;

@SpringBootTest
public class AlertControllerTest {

    @InjectMocks
    private AlertController alertController;

    @Mock
    private AlertService alertService;

    @Mock
    private Model model;

    @BeforeMethod
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void showPageReturnsAlertsViewWhenAlertsExist() {
        // Given
        List<Alert> alerts = Arrays.asList(new Alert(), new Alert());
        when(alertService.getAllAlerts()).thenReturn(alerts);

        // When
        String viewName = alertController.showPage(model);

        // Then
        verify(model).addAttribute("alerts", alerts);
        assertEquals(viewName, "alerts");
    }

    @Test
    public void showPageReturnsAlertsViewWhenNoAlertsExist() {
        // Given
        when(alertService.getAllAlerts()).thenReturn(Arrays.asList());

        // When
        String viewName = alertController.showPage(model);

        // Then
        verify(model).addAttribute("alerts", Arrays.asList());
        assertEquals(viewName, "alerts");
    }
}

