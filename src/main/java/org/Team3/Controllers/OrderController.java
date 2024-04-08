package org.Team3.Controllers;

import org.Team3.Entities.Order;
import org.Team3.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderController class defines endpoints to handle CRUD (Create, Read, Update, Delete) operations related to orders.
 *
 * This controller is responsible for processing HTTP requests and returning appropriate responses for operations
 * on orders such as retrieving all orders, retrieving an order by ID, creating a new order, updating an existing order,
 * and deleting an order.
 *
 * Endpoints are mapped under the "/orders" base path.
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Displays the orders page.
     *
     * @return String representing the view name for the orders page.
     */
    @GetMapping
    public String showPage() {
        return "orders";
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return ResponseEntity containing a List of Order objects representing all orders.
     *         Returns HTTP status code OK (200) on success.
     */
    @GetMapping("/{all}")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * Retrieves an order by its ID from the database.
     *
     * @param id Long representing the ID of the order to retrieve.
     * @return ResponseEntity containing an Order object representing the retrieved order.
     *         Returns HTTP status code OK (200) if the order is found.
     *         Returns HTTP status code NOT_FOUND (404) if the order is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    /**
     * Creates a new order in the database.
     *
     * @param order Order object representing the order to create.
     * @return ResponseEntity containing an Order object representing the newly created order.
     *         Returns HTTP status code CREATED (201) on success.
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    /**
     * Updates an existing order in the database.
     *
     * @param id    Long representing the ID of the order to update.
     * @param order Order object representing the updated order data.
     * @return ResponseEntity containing an Order object representing the updated order.
     *         Returns HTTP status code OK (200) if the order is updated successfully.
     *         Returns HTTP status code NOT_FOUND (404) if the order with the given ID is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(id, order);
        if (updatedOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    /**
     * Deletes an order from the database.
     *
     * @param id Long representing the ID of the order to delete.
     * @return ResponseEntity with no content.
     *         Returns HTTP status code NO_CONTENT (204) if the order is deleted successfully.
     *         Returns HTTP status code NOT_FOUND (404) if the order with the given ID is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean deleted = orderService.deleteOrder(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}