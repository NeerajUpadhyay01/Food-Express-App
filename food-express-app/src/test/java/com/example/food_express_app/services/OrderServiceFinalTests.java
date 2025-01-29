package com.example.food_express_app.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.food_express_app.entities.Order;
import com.example.food_express_app.entities.User;
import com.example.food_express_app.repositories.OrderRepository;
import com.example.food_express_app.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class OrderServiceFinalTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    private Order order;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("user@example.com");
        user.setPassword("password");
        user.setName("Test User");

        order = new Order();
        order.setTotalAmount(BigDecimal.valueOf(100.00));
        // Set other properties as needed
    }

    @Test
    public void testPlaceOrder() {
        userRepository.save(user);
        Order placedOrder = orderService.placeOrder(user.getUserId(), order);
        assertNotNull(placedOrder);
    }

    @Test
    public void testGetOrderById() {
        orderRepository.save(order);
        Optional<Order> foundOrder = orderService.getOrderById(order.getOrderId());
        assertTrue(foundOrder.isPresent());
        assertEquals(order.getTotalAmount(), foundOrder.get().getTotalAmount());
    }

    @Test
    public void testUpdateOrderStatus() {
        orderRepository.save(order);
        Order updatedOrder = orderService.updateOrderStatus(order.getOrderId(), Order.Status.PENDING);
        assertEquals(Order.Status.PENDING, updatedOrder.getStatus());
    }

    @Test
    public void testCancelOrder() {
        orderRepository.save(order);
        orderService.cancelOrder(order.getOrderId());
        assertEquals(Order.Status.CANCELLED, orderService.getOrderById(order.getOrderId()).get().getStatus());
    }
}
