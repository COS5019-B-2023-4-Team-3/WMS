package org.Team3.Services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.Team3.Entities.Alert;
import org.Team3.Entities.Product;
import org.Team3.Repositories.AlertRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AlertServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private AlertRepository alertRepository;


    private AlertService alertService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        alertService = new AlertService(alertRepository, productService);
    }

    @Test
    void shouldReturnAllAlerts() {
        Alert alert = new Alert();
        List<Alert> alerts = alertService.getAllAlerts();

        when(alertRepository.findAll()).thenReturn(Arrays.asList(alert));

        assertEquals(0, alerts.size());
    }

    @Test
    void shouldCheckInventoryExpiration() {
        Product expiredProduct = new Product();
        expiredProduct.setName("Expired Product");
        when(productService.getExpiredProducts(any(LocalDate.class))).thenReturn(Arrays.asList(expiredProduct));

        alertService.checkInventoryExpiration();

        verify(productService, times(1)).getExpiredProducts(any(LocalDate.class));
    }

    @Test
    void shouldNotSendAlertWhenNoInventoryExpiration() {
        when(productService.getExpiredProducts(any(LocalDate.class))).thenReturn(Arrays.asList());

        alertService.checkInventoryExpiration();

        verify(productService, times(1)).getExpiredProducts(any(LocalDate.class));
    }

    @Test
    void shouldCheckLowStockLevels() {
        Product lowStockProduct = new Product();
        lowStockProduct.setName("Low Stock Product");
        when(productService.getLowStockProducts()).thenReturn(Arrays.asList(lowStockProduct));

        alertService.checkLowStockLevels();

        verify(productService, times(1)).getLowStockProducts();
    }

    @Test
    void shouldNotSendAlertWhenNoLowStockLevels() {
        when(productService.getLowStockProducts()).thenReturn(Arrays.asList());

        alertService.checkLowStockLevels();

        verify(productService, times(1)).getLowStockProducts();
    }
}