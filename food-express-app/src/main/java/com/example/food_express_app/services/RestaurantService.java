package com.example.food_express_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food_express_app.entities.Restaurant;
import com.example.food_express_app.repositories.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(restaurant.getId());
        if (existingRestaurant.isPresent()) {
            Restaurant updatedRestaurant = existingRestaurant.get();
            updatedRestaurant.setName(restaurant.getName());
            updatedRestaurant.setAddress(restaurant.getAddress());
            // ...update other fields as necessary...
            return restaurantRepository.save(updatedRestaurant);
        } else {
            throw new RuntimeException("Restaurant not found");
        }
    }

    public void deleteRestaurant(Long id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
        } else {
            throw new RuntimeException("Restaurant not found");
        }
    }

    public Restaurant loginRestaurant(String contact, String password) {
        Restaurant restaurant = restaurantRepository.findByContact(contact)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        if (restaurant.getPassword().equals(password)) {
            return restaurant;
        } else {
            throw new RuntimeException("Invalid password");
        }
    }
}
