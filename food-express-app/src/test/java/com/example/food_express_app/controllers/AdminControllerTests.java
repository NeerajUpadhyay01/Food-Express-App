package com.example.food_express_app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AdminController.class)
public class AdminControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Setup code if needed
    }

    @Test
    public void testGetAllAdmins() throws Exception {
        mockMvc.perform(get("/admins"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAdminById() throws Exception {
        mockMvc.perform(get("/admins/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAdmin() throws Exception {
        mockMvc.perform(post("/admins/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateAdmin() throws Exception {
        mockMvc.perform(put("/admins/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAdmin() throws Exception {
        mockMvc.perform(delete("/admins/1/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testLoginAdmin() throws Exception {
        mockMvc.perform(post("/admins/login"))
                .andExpect(status().isOk());
    }
}
