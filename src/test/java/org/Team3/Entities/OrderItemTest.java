package org.Team3.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrderItemTest {

    private OrderItem orderItem;
    private Order order;
    private Product product;

    @BeforeEach
    void setUp() {
        orderItem = new OrderItem();
        order = new Order();
        product = new Product();
    }

    @Test
    void testOrderItemProperties() {
        orderItem.setId(1L);
        assertEquals(1L, orderItem.getId());

        orderItem.setOrder(order);
        assertEquals(order.toString(), orderItem.getOrder().toString());

        orderItem.setProduct(product);
        assertEquals(product.toString(), orderItem.getProduct().toString());

        orderItem.setQuantity(10);
        assertEquals(10, orderItem.getQuantity());

        orderItem.setSellingPrice(20.25);
        assertEquals(20.25, orderItem.getSellingPrice());

        orderItem.setCost(2.12);
        assertEquals(2.12, orderItem.getCost());

        //TODO: Remove profit tracking from SQL schema and calculate profit on the fly
        orderItem.setProfit(11);
        assertEquals(11, orderItem.getProfit());
    }
}