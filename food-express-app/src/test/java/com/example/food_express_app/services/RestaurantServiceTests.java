// package com.example.food_express_app.services;

// import com.example.food_express_app.models.Restaurant;
// import com.example.food_express_app.repositories.RestaurantRepository;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import java.util.Arrays;
// import java.util.Optional;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.*;

// public class RestaurantServiceTests {

// @Mock
// private RestaurantRepository restaurantRepository;

// @InjectMocks
// private RestaurantService restaurantService;

// public RestaurantServiceTests() {
// MockitoAnnotations.openMocks(this);
// }

// @Test
// public void testGetAllRestaurants() {
// Restaurant restaurant1 = new Restaurant();
// restaurant1.setName("Restaurant 1");
// Restaurant restaurant2 = new Restaurant();
// restaurant2.setName("Restaurant 2");

// when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant1,
// restaurant2));

// assertThat(restaurantService.getAllRestaurants()).hasSize(2).contains(restaurant1,
// restaurant2);
// }

// @Test
// public void testGetRestaurantById() {
// Restaurant restaurant = new Restaurant();
// restaurant.setName("Restaurant 1");

// when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

// assertThat(restaurantService.getRestaurantById(1L)).isEqualTo(Optional.of(restaurant));
// }

// @Test
// public void testCreateRestaurant() {
// Restaurant restaurant = new Restaurant();
// restaurant.setName("New Restaurant");

// when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

// assertThat(restaurantService.createRestaurant(restaurant)).isEqualTo(restaurant);
// }

// @Test
// public void testUpdateRestaurant() {
// Restaurant restaurant = new Restaurant();
// restaurant.setName("Updated Restaurant");

// when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

// assertThat(restaurantService.updateRestaurant(restaurant)).isEqualTo(restaurant);
// }

// @Test
// public void testDeleteRestaurant() {
// doNothing().when(restaurantRepository).deleteById(1L);

// restaurantService.deleteRestaurant(1L);

// verify(restaurantRepository, times(1)).deleteById(1L);
// }
// }
