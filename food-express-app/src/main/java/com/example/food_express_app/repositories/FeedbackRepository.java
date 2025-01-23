package com.example.food_express_app.repositories;

import com.example.food_express_app.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByRestaurant_RestaurantId(Long restaurantId);

    List<Feedback> findByOrder_OrderId(Long orderId);
}
