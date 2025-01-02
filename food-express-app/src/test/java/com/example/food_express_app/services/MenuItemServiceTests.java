// package com.example.food_express_app.services;

// import org.junit.jupiter.api.Test;

// import com.example.food_express_app.entities.MenuItem;

// import static org.junit.jupiter.api.Assertions.*;
// import java.util.List;

// public class MenuItemServiceTests {

// @Test
// void testGetAvailableItems() {
// // Arrange
// MenuItemService service = new MenuItemService();
// // Act
// List<MenuItem> items = service.getAvailableItems();
// // Assert
// assertNotNull(items);
// assertFalse(items.isEmpty());
// }

// @Test
// void testAddMenuItem() {
// // Arrange
// MenuItemService service = new MenuItemService();
// MenuItem newItem = new MenuItem("New Item", 10.99, 100);
// // Act
// service.addMenuItem(newItem);
// // Assert
// assertTrue(service.getAvailableItems().contains(newItem));
// }

// @Test
// void testRemoveMenuItem() {
// // Arrange
// MenuItemService service = new MenuItemService();
// MenuItem item = new MenuItem("Item to Remove", 5.99, 50);
// service.addMenuItem(item);
// // Act
// service.removeMenuItem(item);
// // Assert
// assertFalse(service.getAvailableItems().contains(item));
// }

// @Test
// void testUpdateMenuItem() {
// // Arrange
// MenuItemService service = new MenuItemService();
// MenuItem item = new MenuItem("Item to Update", 7.99, 30);
// service.addMenuItem(item);
// MenuItem updatedItem = new MenuItem("Updated Item", 8.99, 30);
// // Act
// service.updateMenuItem(item, updatedItem);
// // Assert
// assertTrue(service.getAvailableItems().contains(updatedItem));
// assertFalse(service.getAvailableItems().contains(item));
// }

// @Test
// void testUpdateStock() {
// // Arrange
// MenuItemService service = new MenuItemService();
// MenuItem item = new MenuItem("Item to Update Stock", 6.99, 20);
// service.addMenuItem(item);
// int newStock = 50;
// // Act
// service.updateStock(item, newStock);
// // Assert
// assertEquals(newStock, item.getStock());
// }
// }
