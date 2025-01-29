package com.example.food_express_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.food_express_app.entities.User;
import com.example.food_express_app.entities.Restaurant;
import com.example.food_express_app.entities.Feedback;
import com.example.food_express_app.services.UserService;
import com.example.food_express_app.services.RestaurantService;
import com.example.food_express_app.services.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminManagementController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/feedbacks")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@ModelAttribute User user) {
        User createdUser = userService.registerUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/restaurants")
    public ResponseEntity<Restaurant> createRestaurant(@ModelAttribute Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(createdRestaurant);
    }

    @PostMapping("/feedbacks/{orderId}")
    public ResponseEntity<Feedback> createFeedback(@PathVariable Long orderId, @ModelAttribute Feedback feedback) {
        Feedback createdFeedback = feedbackService.submitFeedback(orderId, feedback);
        return ResponseEntity.ok(createdFeedback);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/feedbacks/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
