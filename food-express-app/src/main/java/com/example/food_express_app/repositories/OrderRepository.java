package com.example.food_express_app.repositories;

import com.example.food_express_app.entities.Order;
import com.example.food_express_app.entities.Restaurant;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByRestaurant(Restaurant restaurant);

    List<Order> findByRestaurantAndCreatedAt(Restaurant restaurant, LocalDate date);

    List<Order> findByRestaurantAndCreatedAtBetween(Restaurant restaurant, LocalDate startDate, LocalDate endDate);
}
