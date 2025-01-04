package com.example.food_express_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.food_express_app.entities.Restaurant;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name);

    Optional<Restaurant> findByContact(String contact);

    Optional<Restaurant> findByEmail(String email);
}