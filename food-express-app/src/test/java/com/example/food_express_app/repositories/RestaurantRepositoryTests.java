package com.example.food_express_app.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.food_express_app.entities.Restaurant;

import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RestaurantRepositoryTests {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void testFindByName() {
        // Arrange
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setEmail("testrestaurant@example.com");
        restaurant.setPassword("Test Password");
        restaurant.setContact("12345");
        restaurant.setAddress("Test Address");
        restaurant.setOpenTime("11:00");
        restaurant.setCloseTime("12:00");
        restaurantRepository.save(restaurant);

        // Act
        Optional<Restaurant> foundRestaurant = restaurantRepository.findByName("Test Restaurant");

        // Assert
        assertThat(foundRestaurant).isPresent();
        assertThat(foundRestaurant.get().getName()).isEqualTo("Test Restaurant");
    }
}
