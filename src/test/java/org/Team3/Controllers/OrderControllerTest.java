package org.Team3.Controllers;
import org.Team3.Entities.Order;
import org.Team3.Services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllOrdersReturnsListOfOrders() {
        List<Order> expectedOrders = Arrays.asList(new Order(), new Order());
        when(orderService.getAllOrders()).thenReturn(expectedOrders);

        ResponseEntity<List<Order>> response = orderController.getAllOrders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrders, response.getBody());
    }

    @Test
    public void getOrderByIdReturnsOrderWhenExists() {
        Order expectedOrder = new Order();
        when(orderService.getOrderById(1L)).thenReturn(expectedOrder);

        ResponseEntity<Order> response = orderController.getOrderById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrder, response.getBody());
    }

    @Test
    public void getOrderByIdReturnsNotFoundWhenDoesNotExist() {
        when(orderService.getOrderById(1L)).thenReturn(null);

        ResponseEntity<Order> response = orderController.getOrderById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createOrderReturnsCreatedOrder() {
        Order expectedOrder = new Order();
        when(orderService.createOrder(expectedOrder)).thenReturn(expectedOrder);

        ResponseEntity<Order> response = orderController.createOrder(expectedOrder);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedOrder, response.getBody());
    }

    @Test
    public void updateOrderReturnsUpdatedOrderWhenExists() {
        Order expectedOrder = new Order();
        when(orderService.updateOrder(1L, expectedOrder)).thenReturn(expectedOrder);

        ResponseEntity<Order> response = orderController.updateOrder(1L, expectedOrder);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrder, response.getBody());
    }

    @Test
    public void updateOrderReturnsNotFoundWhenDoesNotExist() {
        Order order = new Order();
        when(orderService.updateOrder(1L, order)).thenReturn(null);

        ResponseEntity<Order> response = orderController.updateOrder(1L, order);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteOrderReturnsNoContentWhenExists() {
        when(orderService.deleteOrder(1L)).thenReturn(true);

        ResponseEntity<Void> response = orderController.deleteOrder(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void deleteOrderReturnsNotFoundWhenDoesNotExist() {
        when(orderService.deleteOrder(1L)).thenReturn(false);

        ResponseEntity<Void> response = orderController.deleteOrder(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}