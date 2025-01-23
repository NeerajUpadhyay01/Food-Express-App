package com.example.food_express_app.services;

import com.example.food_express_app.entities.Feedback;
import com.example.food_express_app.entities.Order;
import com.example.food_express_app.entities.Restaurant;
import com.example.food_express_app.repositories.FeedbackRepository;
import com.example.food_express_app.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Feedback submitFeedback(Long orderId, Feedback feedback) {
        if (orderId == null || feedback == null) {
            throw new IllegalArgumentException("Order ID and feedback cannot be null");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Restaurant restaurant = order.getRestaurant();

        feedback.setOrder(order);
        feedback.setUser(order.getUser());
        feedback.setRestaurant(restaurant);

        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbackByRestaurant(Long restaurantId) {
        return feedbackRepository.findByRestaurant_RestaurantId(restaurantId);
    }

    public List<Feedback> getFeedbackByOrder(Long orderId) {
        return feedbackRepository.findByOrder_OrderId(orderId);
    }

    public Feedback updateFeedback(Long id, String review, int rating) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedback.setReview(review);
        feedback.setRating(rating);
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    public double getAverageRating(Long restaurantId) {
        List<Feedback> feedbacks = feedbackRepository.findByRestaurant_RestaurantId(restaurantId);
        return feedbacks.stream().mapToDouble(Feedback::getRating).average().orElse(0.0);
    }
}
