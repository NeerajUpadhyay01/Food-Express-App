package com.example.food_express_app.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.food_express_app.entities.MenuItem;

@SpringBootTest
public class MenuItemServiceFinalTests {

    @Autowired
    private MenuItemService menuItemService;

    private MenuItem menuItem;

    @BeforeEach
    public void setUp() {
        menuItem = new MenuItem();
        menuItem.setName("Pizza");
        menuItem.setPrice(9.99);
        // Set other properties as needed
    }

    @Test
    public void testAddMenuItem() {
        // Act
        menuItemService.addMenuItem(1L, menuItem); // Assuming restaurant ID is 1
        // Assert
        assertNotNull(menuItemService.getMenuItemById(menuItem.getMenuItemId()));
    }

    @Test
    public void testRemoveMenuItem() {
        // Arrange
        menuItemService.addMenuItem(1L, menuItem); // Assuming restaurant ID is 1
        // Act
        menuItemService.removeMenuItem(menuItem.getMenuItemId());
        // Assert
        assertThrows(RuntimeException.class, () -> menuItemService.getMenuItemById(menuItem.getMenuItemId()));
    }

    @Test
    public void testUpdateMenuItem() {
        // Arrange
        menuItemService.addMenuItem(1L, menuItem); // Assuming restaurant ID is 1
        menuItem.setName("Updated Pizza");
        // Act
        MenuItem updatedItem = menuItemService.updateMenuItem(menuItem, "newImageUrl");
        // Assert
        assertEquals("Updated Pizza", updatedItem.getName());
    }
}
