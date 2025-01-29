package com.example.food_express_app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CartController.class)
public class CartControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Setup code if needed
    }

    @Test
    public void testAddCartItem() throws Exception {
        mockMvc.perform(post("/cart/items/1/1?quantity=2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCartItems() throws Exception {
        mockMvc.perform(get("/cart/items/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testIncreaseCartItemQuantity() throws Exception {
        mockMvc.perform(patch("/cart/items/1/1/increase"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDecreaseCartItemQuantity() throws Exception {
        mockMvc.perform(patch("/cart/items/1/1/decrease"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRemoveCartItem() throws Exception {
        mockMvc.perform(delete("/cart/items/1/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testClearCart() throws Exception {
        mockMvc.perform(delete("/cart/items/1"))
                .andExpect(status().isNoContent());
    }
}
