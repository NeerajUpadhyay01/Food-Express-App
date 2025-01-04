package com.example.food_express_app.services;

import com.example.food_express_app.entities.Restaurant;
import com.example.food_express_app.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class RestaurantServiceTests {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    public RestaurantServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    private Restaurant restaurant1, restaurant2;

    @BeforeEach
    void setUp() {
        restaurant1 = new Restaurant();
        restaurant1.setId(1L);
        restaurant1.setName("Restaurant 1");
        restaurant1.setEmail("restaurant1@example.com");

        restaurant2 = new Restaurant();
        restaurant2.setId(2L);
        restaurant2.setName("Restaurant 2");
        restaurant2.setEmail("restaurant2@example.com");
    }

    @Test
    public void testGetAllRestaurants() {
        when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant1, restaurant2));

        assertThat(restaurantService.getAllRestaurants()).hasSize(2).contains(restaurant1, restaurant2);
    }

    @Test
    public void testGetRestaurantById() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant1));

        Optional<Restaurant> foundRestaurant = restaurantService.getRestaurantById(1L);
        assertThat(foundRestaurant).isPresent();
        assertThat(foundRestaurant.get()).isEqualTo(restaurant1);
    }

    @Test
    public void testCreateRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("New Restaurant");
        restaurant.setEmail("newrestaurant@example.com");

        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        assertThat(restaurantService.createRestaurant(restaurant)).isEqualTo(restaurant);
    }

    @Test
    public void testUpdateRestaurant() {
        restaurant1.setName("Updated Restaurant");

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant1));
        when(restaurantRepository.save(restaurant1)).thenReturn(restaurant1);

        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurant1.getId(), restaurant1);
        assertThat(updatedRestaurant.getName()).isEqualTo("Updated Restaurant");
    }

    @Test
    public void testDeleteRestaurant() {
        when(restaurantRepository.existsById(1L)).thenReturn(true);
        doNothing().when(restaurantRepository).deleteById(1L);

        restaurantService.deleteRestaurant(1L);

        verify(restaurantRepository, times(1)).deleteById(1L);
    }
}
