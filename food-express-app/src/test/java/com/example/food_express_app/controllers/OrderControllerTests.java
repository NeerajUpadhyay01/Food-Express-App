package com.example.food_express_app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Setup code if needed
    }

    @Test
    public void testPlaceOrder() throws Exception {
        mockMvc.perform(post("/orders/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOrderById() throws Exception {
        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllOrders() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateOrderStatus() throws Exception {
        mockMvc.perform(put("/orders/1/status"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCancelOrder() throws Exception {
        mockMvc.perform(delete("/orders/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetOrderItems() throws Exception {
        mockMvc.perform(get("/orders/1/items"))
                .andExpect(status().isOk());
    }
}
