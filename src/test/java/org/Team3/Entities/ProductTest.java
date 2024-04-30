package org.Team3.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void testProductProperties() {
        product.setId(1L);
        assertEquals(1L, product.getId());

        product.setName("Test Product");
        assertEquals("Test Product", product.getName());

        product.setSkuCode("123456");
        assertEquals("123456", product.getSkuCode());

        product.setDescription("Test Product Description");
        assertEquals("Test Product Description", product.getDescription());

        product.setShelfLife(7);
        assertEquals(7, product.getShelfLife());

        LocalDate testDate = LocalDate.now();
        product.setExpiryDate(testDate);
        assertEquals(testDate, product.getExpiryDate());

        product.setSellingPrice(12.25);
        assertEquals(12.25, product.getSellingPrice());

        product.setUnitCost(1.23);
        assertEquals(1.23, product.getUnitCost());

        product.setCurrentStockLevel(100);
        assertEquals(100, product.getCurrentStockLevel());

        product.setMinStockLevel(12);
        assertEquals(12, product.getMinStockLevel());
    }
}