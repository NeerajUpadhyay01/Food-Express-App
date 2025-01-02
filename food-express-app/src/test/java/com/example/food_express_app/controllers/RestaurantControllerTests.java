package com.example.food_express_app.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.food_express_app.entities.Restaurant;
import com.example.food_express_app.services.RestaurantService;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void getAllRestaurants() throws Exception {
        when(restaurantService.getAllRestaurants()).thenReturn(Arrays.asList(new Restaurant(), new Restaurant()));
        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andDo(print());
    }

    @Test
    public void getRestaurantById() throws Exception {
        Restaurant restaurant = new Restaurant();
        when(restaurantService.getRestaurantById(1L)).thenReturn(restaurant);
        mockMvc.perform(get("/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    public void createRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant();
        when(restaurantService.createRestaurant(any(Restaurant.class))).thenReturn(restaurant);
        mockMvc.perform(post("/restaurants")
                .contentType("application/json")
                .content("{\"name\":\"Test Restaurant\"}"))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void updateRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant();
        when(restaurantService.updateRestaurant(eq(1L), any(Restaurant.class))).thenReturn(restaurant);
        mockMvc.perform(put("/restaurants/1")
                .contentType("application/json")
                .content("{\"name\":\"Updated Restaurant\"}"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteRestaurant() throws Exception {
        doNothing().when(restaurantService).deleteRestaurant(1L);
        mockMvc.perform(delete("/restaurants/1"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
