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
    }

    @Test
    void getId() {
        order.setId(1L);
        assertEquals(1L, order.getId());
    }

    @Test
    void getDate() {
        assertEquals(testDate, order.getDate());
    }

    @Test
    void getStatus() {
        order.setStatus("Shipped");
        assertEquals("Shipped", order.getStatus());
    }

    @Test
    void getVendorId() {
        order.setVendorId(1L);
        assertEquals(1L, order.getVendorId());
    }
}