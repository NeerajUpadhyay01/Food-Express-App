package com.example.food_express_app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FeedbackController.class)
public class FeedbackControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Setup code if needed
    }

    @Test
    public void testSubmitFeedback() throws Exception {
        mockMvc.perform(post("/feedback/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFeedbackByRestaurant() throws Exception {
        mockMvc.perform(get("/feedback/restaurant/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFeedbackByOrder() throws Exception {
        mockMvc.perform(get("/feedback/order/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateFeedback() throws Exception {
        mockMvc.perform(put("/feedback/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFeedback() throws Exception {
        mockMvc.perform(delete("/feedback/1"))
                .andExpect(status().isNoContent());
    }
}
