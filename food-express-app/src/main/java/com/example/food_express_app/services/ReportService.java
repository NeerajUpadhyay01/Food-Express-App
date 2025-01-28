package com.example.food_express_app.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food_express_app.entities.CartItem;
import com.example.food_express_app.entities.MenuItem;
import com.example.food_express_app.entities.Order;
import com.example.food_express_app.entities.Order.Status;
import com.example.food_express_app.entities.Report;
import com.example.food_express_app.entities.Restaurant;
import com.example.food_express_app.repositories.MenuItemRepository;
import com.example.food_express_app.repositories.OrderRepository;
import com.example.food_express_app.repositories.RestaurantRepository;

@Service
public class ReportService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public Report getDailyReport(Long restaurantId, LocalDate date) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        List<Order> orders = orderRepository.findByRestaurantAndCreatedAt(restaurant, date);

        Report dailyReport = new Report();
        dailyReport.setStartDate(date);
        dailyReport.setEndDate(date);
        dailyReport.setTotalRevenue(calculateTotalRevenue(orders));
        dailyReport.setOrdersServed(calculateOrdersServed(orders));
        dailyReport.setOrderStatusCount(generateOrderStatusReport(restaurantId, date, date));
        dailyReport.setBestPreferredMenuItem(getBestPreferredMenuItem(restaurantId, date, date));
        return dailyReport;
    }

    public Report getWeeklyReport(Long restaurantId, LocalDate startDate, LocalDate endDate) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RuntimeException("Restaurant not found"));
        List<Order> orders = orderRepository.findByRestaurantAndCreatedAtBetween(restaurant, startDate, endDate);

        Report weeklyReport = new Report();
        weeklyReport.setStartDate(startDate);
        weeklyReport.setEndDate(endDate);
        weeklyReport.setTotalRevenue(calculateTotalRevenue(orders));
        weeklyReport.setOrdersServed(calculateOrdersServed(orders));
        weeklyReport.setOrderStatusCount(generateOrderStatusReport(restaurantId, startDate, endDate));
        weeklyReport.setBestPreferredMenuItem(getBestPreferredMenuItem(restaurantId, startDate, endDate));

        return weeklyReport;
    }

    public Report getMonthlyReport(Long restaurantId, LocalDate startDate, LocalDate endDate) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RuntimeException("Restaurant not found"));
        List<Order> orders = orderRepository.findByRestaurantAndCreatedAtBetween(restaurant, startDate, endDate);

        Report monthlyReport = new Report();
        monthlyReport.setStartDate(startDate);
        monthlyReport.setEndDate(endDate);
        monthlyReport.setTotalRevenue(calculateTotalRevenue(orders));
        monthlyReport.setOrdersServed(calculateOrdersServed(orders));
        monthlyReport.setOrderStatusCount(generateOrderStatusReport(restaurantId, startDate, endDate));
        monthlyReport.setBestPreferredMenuItem(getBestPreferredMenuItem(restaurantId, startDate, endDate));

        return monthlyReport;
    }

    public Report getCustomReport(Long restaurantId, LocalDate startDate, LocalDate endDate) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RuntimeException("Restaurant not found"));
        List<Order> orders = orderRepository.findByRestaurantAndCreatedAtBetween(restaurant, startDate, endDate);

        Report customReport = new Report();
        customReport.setStartDate(startDate);
        customReport.setEndDate(endDate);
        customReport.setTotalRevenue(calculateTotalRevenue(orders));
        customReport.setOrdersServed(calculateOrdersServed(orders));
        customReport.setOrderStatusCount(generateOrderStatusReport(restaurantId, startDate, endDate));
        customReport.setBestPreferredMenuItem(getBestPreferredMenuItem(restaurantId, startDate, endDate));

        return customReport;
    }

    public BigDecimal calculateTotalRevenue(List<Order> orders) {
        return orders.stream()
                .map(order -> order.getTotalAmount() != null ? order.getTotalAmount() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private int calculateOrdersServed(List<Order> orders) {
        return orders.size();
    }

    public MenuItem getBestPreferredMenuItem(Long restaurantId, LocalDate startDate, LocalDate endDate) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RuntimeException("Restaurant not found"));
        List<Order> orders = orderRepository.findByRestaurantAndCreatedAtBetween(restaurant, startDate, endDate);
        Map<Long, Long> menuItemCount = new HashMap<>();
        Map<Long, Double> menuItemTotalPrice = new HashMap<>();

        for (Order order : orders) {
            for (CartItem orderItem : order.getCartItems()) {
                Long menuItemId = orderItem.getMenuItemId();

                menuItemCount.put(menuItemId, menuItemCount.getOrDefault(menuItemId, 0L) + 1);

                menuItemTotalPrice.put(menuItemId,
                        menuItemTotalPrice.getOrDefault(menuItemId, 0.0) + orderItem.getPrice());
            }
        }

        Long bestPreferredMenuItemId = findBestPreferredMenuItem(menuItemCount, menuItemTotalPrice);

        if (bestPreferredMenuItemId != null) {
            MenuItem bestPreferredMenuItem = menuItemRepository.findById(bestPreferredMenuItemId).orElseThrow(
                    () -> new RuntimeException("Menu item not found"));
            return bestPreferredMenuItem;
        }

        return null;
    }

    private Long findBestPreferredMenuItem(Map<Long, Long> menuItemCount, Map<Long, Double> menuItemTotalPrice) {
        return menuItemCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Map<Status, Long> generateOrderStatusReport(Long restaurantId, LocalDate startDate, LocalDate endDate) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new RuntimeException("Restaurant not found"));
        List<Order> orders = orderRepository.findByRestaurantAndCreatedAtBetween(restaurant, startDate, endDate);

        Map<Status, Long> orderStatusCount = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));

        return orderStatusCount;
    }
}
