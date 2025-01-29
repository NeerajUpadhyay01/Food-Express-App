package com.example.food_express_app.controllers;

import com.example.food_express_app.entities.Order;
import com.example.food_express_app.entities.CartItem;
import com.example.food_express_app.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long userId, @ModelAttribute Order order) {
        Order placedOrder = orderService.placeOrder(userId, order);
        return ResponseEntity.ok(placedOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable Long orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam Order.Status status) {
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<CartItem>> getOrderItems(@PathVariable Long orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        if (order.isPresent()) {
            List<CartItem> orderItems = order.get().getCartItems();
            return ResponseEntity.ok(orderItems);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}