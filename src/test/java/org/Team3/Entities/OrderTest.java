package org.Team3.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OrderTest {

    Order order;
    LocalDate testDate;

    @BeforeEach
    void setUp() {
        order = new Order();
        testDate = LocalDate.now();
        order.setDate(testDate);
        order.setId(1L);
        order.setStatus("Shipped");
        order.setVendorId(1L);
    }

    @Test
    void testOrderProperties() {
        assertEquals(1L, order.getId());
        assertEquals(testDate, order.getDate());
        assertEquals("Shipped", order.getStatus());
        assertEquals(1L, order.getVendorId());
    }
}