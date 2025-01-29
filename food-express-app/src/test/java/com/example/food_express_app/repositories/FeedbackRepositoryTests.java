package com.example.food_express_app.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.food_express_app.entities.Feedback;

import java.util.List;

@DataJdbcTest
@ActiveProfiles("test")
public class FeedbackRepositoryTests {

    @Autowired
    private FeedbackRepository feedbackRepository;

    private Feedback feedback;

    @BeforeEach
    public void setUp() {
        feedback = new Feedback();
        feedback.setReview("Great service!");
        feedback.setRating(5);
        // Set other properties as needed
    }

    @Test
    public void testFindByRestaurant_RestaurantId() {
        feedbackRepository.save(feedback);
        List<Feedback> foundFeedback = feedbackRepository.findByRestaurant_RestaurantId(1L);
        assertFalse(foundFeedback.isEmpty());
    }
}
