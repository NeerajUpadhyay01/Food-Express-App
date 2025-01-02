package com.example.food_express_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.food_express_app.entities.Restaurant;
import com.example.food_express_app.services.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping("/register")
    public Restaurant createRestaurant(@ModelAttribute Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }

    @PostMapping("/login")
    public Restaurant loginRestaurant(@RequestParam String contact, @RequestParam String password) {
        return restaurantService.loginRestaurant(contact, password);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(
            @PathVariable Long id,
            @ModelAttribute Restaurant restaurant) {
        return restaurantService.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok().build();
    }
}
