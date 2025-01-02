// package com.example.food_express_app.controllers;

// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import com.example.food_express_app.entities.MenuItem;
// import com.example.food_express_app.services.MenuItemService;

// import java.util.Arrays;
// import java.util.List;

// public class MenuItemControllerTests {

// @Mock
// private MenuItemService menuItemService;

// @InjectMocks
// private MenuItemController menuItemController;

// public MenuItemControllerTests() {
// MockitoAnnotations.openMocks(this);
// }

// @Test
// void testShowMenu() {
// // Arrange
// List<MenuItem> menuItems = Arrays.asList(new MenuItem("Pizza", 10.0), new
// MenuItem("Burger", 5.0));
// when(menuItemService.getAllMenuItems()).thenReturn(menuItems);

// // Act
// List<MenuItem> result = menuItemController.showMenu();

// // Assert
// assertEquals(2, result.size());
// verify(menuItemService, times(1)).getAllMenuItems();
// }

// @Test
// void testAddMenuItem() {
// // Arrange
// MenuItem menuItem = new MenuItem("Pasta", 8.0);
// when(menuItemService.addMenuItem(menuItem)).thenReturn(menuItem);

// // Act
// MenuItem result = menuItemController.addMenuItem(menuItem);

// // Assert
// assertEquals("Pasta", result.getName());
// assertEquals(8.0, result.getPrice());
// verify(menuItemService, times(1)).addMenuItem(menuItem);
// }

// @Test
// void testDeleteMenuItem() {
// // Arrange
// String itemName = "Pizza";
// doNothing().when(menuItemService).deleteMenuItem(itemName);

// // Act
// menuItemController.deleteMenuItem(itemName);

// // Assert
// verify(menuItemService, times(1)).deleteMenuItem(itemName);
// }

// @Test
// void testUpdateMenuItem() {
// // Arrange
// MenuItem menuItem = new MenuItem("Pizza", 12.0);
// when(menuItemService.updateMenuItem(menuItem)).thenReturn(menuItem);

// // Act
// MenuItem result = menuItemController.updateMenuItem(menuItem);

// // Assert
// assertEquals("Pizza", result.getName());
// assertEquals(12.0, result.getPrice());
// verify(menuItemService, times(1)).updateMenuItem(menuItem);
// }

// @Test
// void testUpdateStock() {
// // Arrange
// String itemName = "Pizza";
// int newStock = 20;
// doNothing().when(menuItemService).updateStock(itemName, newStock);

// // Act
// menuItemController.updateStock(itemName, newStock);

// // Assert
// verify(menuItemService, times(1)).updateStock(itemName, newStock);
// }
// }
