package org.Team3.Services;
import org.Team3.Entities.Order;
import org.Team3.Repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @DisplayName("Get All Orders Returns All Orders")
    @Test
    public void getAllOrdersReturnsAllOrders() {
        Order order1 = new Order();
        Order order2 = new Order();
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.getAllOrders();

        assertEquals(2, orders.size());
        verify(orderRepository, times(1)).findAll();
    }

    @DisplayName("Get Order By Id Returns Correct Order")
    @Test
    public void getOrderByIdReturnsCorrectOrder() {
        Order order = new Order();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order foundOrder = orderService.getOrderById(1L);

        assertEquals(order, foundOrder);
        verify(orderRepository, times(1)).findById(1L);
    }

    @DisplayName("Get Order By Id Returns Null When Order Does Not Exist")
    @Test
    public void getOrderByIdReturnsNullWhenOrderDoesNotExist() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        Order foundOrder = orderService.getOrderById(1L);

        assertNull(foundOrder);
        verify(orderRepository, times(1)).findById(1L);
    }

    @DisplayName("Create Order Returns Created Order")
    @Test
    public void createOrderReturnsCreatedOrder() {
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        assertEquals(order, createdOrder);
        verify(orderRepository, times(1)).save(order);
    }

    @DisplayName("Update Order Returns Updated Order")
    @Test
    public void updateOrderReturnsUpdatedOrder() {
        Order order = new Order();
        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderRepository.save(order)).thenReturn(order);

        Order updatedOrder = orderService.updateOrder(1L, order);

        assertEquals(order, updatedOrder);
        verify(orderRepository, times(1)).save(order);
    }

    @DisplayName("Update Order Returns Null When Order Does Not Exist")
    @Test
    public void updateOrderReturnsNullWhenOrderDoesNotExist() {
        Order order = new Order();
        when(orderRepository.existsById(1L)).thenReturn(false);

        Order updatedOrder = orderService.updateOrder(1L, order);

        assertNull(updatedOrder);
        verify(orderRepository, times(0)).save(order);
    }

    @DisplayName("Delete Order Returns True When Order Exists")
    @Test
    public void deleteOrderReturnsTrueWhenOrderExists() {
        when(orderRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = orderService.deleteOrder(1L);

        assertTrue(isDeleted);
        verify(orderRepository, times(1)).deleteById(1L);
    }

    @DisplayName("Delete Order Returns False When Order Does Not Exist")
    @Test
    public void deleteOrderReturnsFalseWhenOrderDoesNotExist() {
        when(orderRepository.existsById(1L)).thenReturn(false);

        boolean isDeleted = orderService.deleteOrder(1L);

        assertFalse(isDeleted);
        verify(orderRepository, times(0)).deleteById(1L);
    }
}