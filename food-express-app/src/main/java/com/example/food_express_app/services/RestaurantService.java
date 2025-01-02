package com.example.food_express_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food_express_app.entities.Restaurant;
import com.example.food_express_app.repositories.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) {
        Restaurant restaurant = getRestaurantById(id);

        restaurant.setName(restaurantDetails.getName());
        restaurant.setAddress(restaurantDetails.getAddress());
        restaurant.setContact(restaurantDetails.getContact());
        restaurant.setOpenTime(restaurantDetails.getOpenTime());
        restaurant.setCloseTime(restaurantDetails.getCloseTime());
        restaurant.setPassword(restaurantDetails.getPassword());

        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        Restaurant restaurant = getRestaurantById(id);
        restaurantRepository.delete(restaurant);
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
