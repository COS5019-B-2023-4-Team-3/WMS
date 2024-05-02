package org.Team3.Controllers;
import org.Team3.Entities.Order;
import org.Team3.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.List;
import org.springframework.ui.Model;
/**
 * defines endpoints to handle CRUD operations
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String showPage(Model model) {
        List<Order> orderList = orderService.getAllOrders();
        model.addAttribute("orderList", orderList);
        return "orders";
    }

    @GetMapping("/orders/create")
    public String createOrder() {
        return "orderCreate";
    }

    @GetMapping("/orders/edit")
    public String editOrder() {
        return "orderEdit";
    }

    @GetMapping("/orders/remove")
    public String removeOrder() {
        return "orderRemove";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/orders/create")
    public Order createOrder(@RequestBody Order newOrder) {
        return orderService.createOrder(newOrder);
        //Order createdOrder = orderService.createOrder(order);
        //return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/orders/edit")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(id, order);
        if (updatedOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @PostMapping("/orders/remove")
    public String deleteOrder(@RequestParam("id") Long id) {
        if (id != null){
            orderService.deleteOrder(id);
            return null;
        }
        else {
            return "redirect:/homepage";
        }
    }
}