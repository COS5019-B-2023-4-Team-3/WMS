package org.Team3.Services;
import org.Team3.Entities.Order;
import org.Team3.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *  Responsible for encapsulating business logic related to its corresponding entity and coordinating interactions between controllers and repositories.
 *  Service methods interact with repository classes to perform database operations.
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        if (!orderRepository.existsById(id)) {
            return null; // Order with given id does not exist
        }
        order.setId(id);
        return orderRepository.save(order);
    }

    public boolean deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            return false; // Order with given id does not exist
        }
        orderRepository.deleteById(id);
        return true;
    }
}
