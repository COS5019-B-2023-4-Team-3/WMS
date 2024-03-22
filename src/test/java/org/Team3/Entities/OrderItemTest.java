package org.Team3.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderItemTest {

    private OrderItem orderItem;
    private Order order;
    private Product product;

    @BeforeEach
    void setUp() {
        orderItem = new OrderItem();
    }

    @Test
    void getId() {
        orderItem.setId(1L);
        assertEquals(1L, orderItem.getId());
    }

    @Test
    void getOrder() {
        order = new Order();
        orderItem.setOrder(order);
        assertEquals(order.toString(), orderItem.getOrder().toString());
    }

    @Test
    void getProduct() {
        product = new Product();
        orderItem.setProduct(product);
        assertEquals(product.toString(), orderItem.getProduct().toString());
    }

    @Test
    void getQuantity() {
        orderItem.setQuantity(10);
        assertEquals(10, orderItem.getQuantity());
    }

    @Test
    void getSellingPrice() {
        orderItem.setSellingPrice(20.25);
        assertEquals(20.25, orderItem.getSellingPrice());
    }

    @Test
    void getCost() {
        orderItem.setCost(2.12);
        assertEquals(2.12, orderItem.getCost());
    }

    //TODO: remove profit tracking from SQL and calculate on the fly
    @Test
    void getProfit() {
        orderItem.setProfit(11);
        assertEquals(11, orderItem.getProfit());
    }
}