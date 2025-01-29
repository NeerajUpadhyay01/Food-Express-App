package com.example.food_express_app.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.food_express_app.entities.Order;
import com.example.food_express_app.entities.Restaurant;

import java.math.BigDecimal;
import java.util.List;

@DataJdbcTest
@ActiveProfiles("test")
public class OrderRepositoryFinalTests {

    @Autowired
    private OrderRepository orderRepository;

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();
        order.setTotalAmount(BigDecimal.valueOf(100.00));
        // Set other properties as needed
    }

    @Test
    public void testFindByRestaurant() {
        orderRepository.save(order);
        List<Order> foundOrders = orderRepository.findByRestaurant(new Restaurant());
        assertFalse(foundOrders.isEmpty());
    }
}
