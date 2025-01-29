package com.example.food_express_app.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.food_express_app.entities.Feedback;
import com.example.food_express_app.entities.Order;
import com.example.food_express_app.repositories.FeedbackRepository;
import com.example.food_express_app.repositories.OrderRepository;

import java.util.List;

@SpringBootTest
public class FeedbackServiceTests {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Feedback feedback;

    @BeforeEach
    public void setUp() {
        feedback = new Feedback();
        feedback.setReview("Great service!");
        feedback.setRating(5);
        // Set other properties as needed
    }

    @Test
    public void testSubmitFeedback() {
        Order order = new Order();
        orderRepository.save(order); // Assuming an order is saved
        feedbackService.submitFeedback(order.getOrderId(), feedback);
        List<Feedback> foundFeedback = feedbackRepository.findByOrder_OrderId(order.getOrderId());
        assertFalse(foundFeedback.isEmpty());
    }

    @Test
    public void testGetFeedbackByRestaurant() {
        // Implement test logic
    }

    @Test
    public void testGetFeedbackByOrder() {
        // Implement test logic
    }

    @Test
    public void testUpdateFeedback() {
        // Implement test logic
    }

    @Test
    public void testDeleteFeedback() {
        // Implement test logic
    }
}
