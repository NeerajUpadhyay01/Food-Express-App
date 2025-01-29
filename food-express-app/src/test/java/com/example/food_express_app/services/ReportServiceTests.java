package com.example.food_express_app.services;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReportService.class)
public class ReportServiceTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Setup code if needed
    }

    @Test
    public void testGenerateDailyReport() throws Exception {
        mockMvc.perform(get("/reports/daily/1?date=2023-01-01"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.reportType").value("daily"));
    }

    @Test
    public void testGenerateWeeklyReport() throws Exception {
        mockMvc.perform(get("/reports/weekly/1?startDate=2023-01-01&endDate=2023-01-07"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.reportType").value("weekly"));
    }

    @Test
    public void testGenerateMonthlyReport() throws Exception {
        mockMvc.perform(get("/reports/monthly/1?startDate=2023-01-01&endDate=2023-01-31"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.reportType").value("monthly"));
    }

    @Test
    public void testGetTotalRevenue() throws Exception {
        mockMvc.perform(get("/reports/1/totalRevenue"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.totalRevenue").isNumber());
    }

    @Test
    public void testGetTotalOrderServed() throws Exception {
        mockMvc.perform(get("/reports/1/totalOrderServed"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.totalOrders").isNumber());
    }

    @Test
    public void testGenerateOrderStatusReport() throws Exception {
        mockMvc.perform(get("/reports/order-status/1?startDate=2023-01-01&endDate=2023-01-31"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.reportType").value("order-status"));
    }

    // Additional tests for edge cases can be added here
}
