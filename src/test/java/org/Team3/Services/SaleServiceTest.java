package org.Team3.Services;
import org.Team3.Entities.Sale;
import org.Team3.Repositories.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SaleServiceTest {
    private SaleRepository saleRepository;
    private SaleService saleService;

    @BeforeEach
    public void setUp() {
        saleRepository = Mockito.mock(SaleRepository.class);
        saleService = new SaleService(saleRepository);
    }

    @DisplayName("Get Sales In Range Returns Correct Sales")
    @Test
    public void getSalesInRangeReturnsCorrectSales() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);
        Sale sale1 = new Sale();
        sale1.setDate(startDate); // set date for sale1
        sale1.setIncome(100L); // set income for sale1
        Sale sale2 = new Sale();
        sale2.setDate(startDate.minusDays(1)); // set date for sale2
        sale2.setIncome(200L); // set income for sale2
        when(saleRepository.getSalesInRange(startDate, endDate)).thenReturn(Arrays.asList(sale1, sale2));

        List<Map<String, Object>> sales = saleService.getSalesInRange(startDate, endDate);

        assertEquals(2, sales.size());
        verify(saleRepository, times(1)).getSalesInRange(startDate, endDate);
    }

    @DisplayName("Get Sales In Range Returns Empty List When No Sales")
    @Test
    public void getSalesInRangeReturnsEmptyListWhenNoSales() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);
        when(saleRepository.getSalesInRange(startDate, endDate)).thenReturn(List.of());

        List<Map<String, Object>> sales = saleService.getSalesInRange(startDate, endDate);

        assertTrue(sales.isEmpty());
        verify(saleRepository, times(1)).getSalesInRange(startDate, endDate);
    }
}