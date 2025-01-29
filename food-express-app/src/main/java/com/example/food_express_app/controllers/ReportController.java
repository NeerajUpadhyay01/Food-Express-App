package com.example.food_express_app.controllers;

import com.example.food_express_app.entities.Order;
import com.example.food_express_app.entities.Order.Status;
import com.example.food_express_app.entities.Report;
import com.example.food_express_app.entities.Restaurant;
import com.example.food_express_app.repositories.OrderRepository;
import com.example.food_express_app.repositories.RestaurantRepository;
import com.example.food_express_app.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/daily/{restaurantId}")
    public Report generateDailyReport(@PathVariable Long restaurantId) {
        LocalDate date = LocalDate.now();
        return reportService.getDailyReport(restaurantId, date);
    }

    @GetMapping("/weekly/{restaurantId}")
    public Report generateWeeklyReport(@PathVariable Long restaurantId) {
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        LocalDate endDate = LocalDate.now();
        return reportService.getWeeklyReport(restaurantId, startDate, endDate);
    }

    @GetMapping("/monthly/{restaurantId}")
    public Report generateMonthlyReport(@PathVariable Long restaurantId) {
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now();
        return reportService.getMonthlyReport(restaurantId, startDate, endDate);
    }

    @GetMapping("/custom/{restaurantId}")
    public Report generateCustomReport(@PathVariable Long restaurantId,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return reportService.getCustomReport(restaurantId, startDate, endDate);
    }

    @GetMapping("/{restaurantId}/totalRevenue")
    public BigDecimal getTotalRevenue(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RuntimeException("Restaurant not found"));
        List<Order> orders = orderRepository.findByRestaurant(restaurant);
        return reportService.calculateTotalRevenue(orders);
    }

    @GetMapping("/{restaurantId}/totalOrderServed")
    public int getTotalOrderServed(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RuntimeException("Restaurant not found"));
        List<Order> orders = orderRepository.findByRestaurant(restaurant);
        return orders.size();
    }

    @GetMapping("/order-status/{restaurantId}")
    public Map<Status, Long> generateOrderStatusReport(
            @PathVariable Long restaurantId,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return reportService.generateOrderStatusReport(restaurantId, startDate, endDate);
    }
}
