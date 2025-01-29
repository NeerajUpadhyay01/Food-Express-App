package com.example.food_express_app.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.food_express_app.entities.Restaurant;
import com.example.food_express_app.repositories.RestaurantRepository;

import java.util.Optional;

@SpringBootTest
public class RestaurantServiceFinalTests {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        // Set other properties as needed
    }

    @Test
    public void testCreateRestaurant() {
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
        assertNotNull(createdRestaurant);
        assertEquals(restaurant.getName(), createdRestaurant.getName());
    }

    @Test
    public void testGetRestaurantById() {
        restaurantRepository.save(restaurant);
        Optional<Restaurant> foundRestaurant = restaurantService.getRestaurantById(restaurant.getRestaurantId());
        assertTrue(foundRestaurant.isPresent());
        assertEquals(restaurant.getName(), foundRestaurant.get().getName());
    }

    @Test
    public void testUpdateRestaurant() {
        restaurantRepository.save(restaurant);
        restaurant.setName("Updated Restaurant");
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurant, "newImageUrl");
        assertEquals("Updated Restaurant", updatedRestaurant.getName());
    }

    @Test
    public void testDeleteRestaurant() {
        restaurantRepository.save(restaurant);
        restaurantService.deleteRestaurant(restaurant.getRestaurantId());
        assertFalse(restaurantRepository.findById(restaurant.getRestaurantId()).isPresent());
    }
}
