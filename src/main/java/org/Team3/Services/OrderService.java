package org.Team3.Services;
import org.Team3.Entities.Order;
import org.Team3.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * OrderService encapsulates business logic related to orders, coordinating interactions between controllers and repositories.
 * It provides methods to perform CRUD (Create, Read, Update, Delete) operations on orders and retrieve specific information related to orders.
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    /**
     * Retrieves all orders from the repository.
     * @return List of all orders
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves an order by its ID.
     * @param id The ID of the order to retrieve
     * @return The order if found, otherwise null
     */
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new order.
     * @param order The order to create
     * @return The newly created order
     */
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Updates an existing order with the provided ID using the details from the new order.
     * @param id The ID of the order to update
     * @param order The updated order
     * @return The updated order if successful, otherwise null
     */
    public Order updateOrder(Long id, Order order) {
        if (!orderRepository.existsById(id)) {
            return null; // Order with given id does not exist
        }
        order.setId(id);
        return orderRepository.save(order);
    }

    /**
     * Deletes an order with the provided ID.
     * @param id The ID of the order to delete
     * @return True if the order was successfully deleted, otherwise false
     */
    public boolean deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            return false; // Order with given id does not exist
        }
        orderRepository.deleteById(id);
        return true;
    }
}
