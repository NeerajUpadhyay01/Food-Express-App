package com.example.food_express_app.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.food_express_app.entities.MenuItem;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
public class MenuItemServiceTests {

    @Autowired
    private MenuItemService service;

    private MenuItem newItem;

    @BeforeEach
    void setUp() {
        // Initialize test data if necessary
        newItem = new MenuItem();
        newItem.setName("New Item");
        newItem.setPreparationTime(30);
        newItem.setCategory("New Item");
        newItem.setAvailable(true);
        newItem.setDescription("New Item");
        newItem.setPrice(4.99);
        newItem.setStockQuantity(40);
    }

    @Test
    void testAddMenuItem() {
        // Act
        service.addMenuItem(newItem);
        // Assert
        assertTrue(service.getAvailableItems().contains(newItem));
    }

    @Test
    void testRemoveMenuItem() {
        // Arrange
        service.addMenuItem(newItem);
        // Act
        service.removeMenuItem(newItem.getId());
        // Assert
        assertFalse(service.getAvailableItems().contains(newItem));
    }

    @Test
    void testGetAvailableItems() {
        // Arrange
        service.addMenuItem(newItem);
        // Act
        List<MenuItem> items = service.getAvailableItems();
        // Assert
        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertTrue(items.contains(newItem));
    }

    @Test
    void testUpdateMenuItem() {
        // Arrange
        service.addMenuItem(newItem);
        newItem.setName("Updated Item");
        // Assert
        assertEquals(newItem, service.updateMenuItem(newItem.getId(), newItem));
    }

    @Test
    void testUpdateStock() {
        // Arrange
        service.addMenuItem(newItem);
        newItem.setStockQuantity(50);
        // Act
        service.updateStock(newItem.getId(), newItem.getStockQuantity(), newItem.isAvailable());
        // Assert
        assertEquals(50, service.getAvailableItems().get(0).getStockQuantity());
    }
}
