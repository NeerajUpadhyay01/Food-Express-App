package com.example.food_express_app.controllers;

import com.example.food_express_app.entities.Feedback;
import com.example.food_express_app.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/{orderId}")
    public ResponseEntity<Feedback> submitFeedback(@PathVariable Long orderId, @ModelAttribute Feedback feedback) {
        Feedback submittedFeedback = feedbackService.submitFeedback(orderId, feedback);
        return ResponseEntity.ok(submittedFeedback);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Feedback>> getFeedbackByRestaurant(@PathVariable Long restaurantId) {
        List<Feedback> feedbacks = feedbackService.getFeedbackByRestaurant(restaurantId);
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<Feedback>> getFeedbackByOrder(@PathVariable Long orderId) {
        List<Feedback> feedbacks = feedbackService.getFeedbackByOrder(orderId);
        return ResponseEntity.ok(feedbacks);
    }

    @PutMapping("/{feedbackId}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long feedbackId, @RequestParam String review,
            @RequestParam int rating) {
        Feedback updatedFeedback = feedbackService.updateFeedback(feedbackId, review, rating);
        return ResponseEntity.ok(updatedFeedback);
    }

    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurant/{restaurantId}/average-rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long restaurantId) {
        double averageRating = feedbackService.getAverageRating(restaurantId);
        return ResponseEntity.ok(averageRating);
    }
}
