package org.Team3.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void getId() {
        product.setId(1L);
        assertEquals(1L, product.getId());
    }

    @Test
    void getName() {
        product.setName("Test Product");
        assertEquals("Test Product", product.getName());
    }

    @Test
    void getSkuCode() {
        product.setSkuCode("123456");
        assertEquals("123456", product.getSkuCode());
    }

    @Test
    void getDescription() {
        product.setDescription("Test Product Description");
        assertEquals("Test Product Description", product.getDescription());
    }

    @Test
    void getShelfLife() {
        product.setShelfLife(7);
        assertEquals(7, product.getShelfLife());
    }

    @Test
    void getExpiryDate() {
        LocalDate testDate = LocalDate.now();
        product.setExpiryDate(testDate);
        assertEquals(testDate, product.getExpiryDate());
    }

    @Test
    void getSellingPrice() {
        product.setSellingPrice(12.25);
        assertEquals(12.25, product.getSellingPrice());
    }

    @Test
    void getUnitCost() {
        product.setUnitCost(1.23);
        assertEquals(1.23, product.getUnitCost());
    }

    @Test
    void getCurrentStockLevel() {
        product.setCurrentStockLevel(100);
        assertEquals(100, product.getCurrentStockLevel());
    }

    @Test
    void getMinStockLevel() {
        product.setMinStockLevel(12);
        assertEquals(12, product.getMinStockLevel());
    }
}