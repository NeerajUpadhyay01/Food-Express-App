package com.example.food_express_app.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.food_express_app.entities.CartItem;
import com.example.food_express_app.entities.MenuItem;
import com.example.food_express_app.entities.User;

import java.util.List;

@SpringBootTest
public class CartServiceTests {

    @Autowired
    private CartService cartService;

    private CartItem cartItem;
    private MenuItem menuItem;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("user@example.com");
        user.setPassword("password");
        user.setName("Test User");

        menuItem = new MenuItem();
        menuItem.setName("Pizza");
        menuItem.setPrice(9.99);

        cartItem = new CartItem();
        cartItem.setQuantity(2);
        cartItem.setMenuItem(menuItem);
        cartItem.setUser(user);
    }

    @Test
    public void testAddCartItem() {
        // Act
        List<CartItem> cartItems = cartService.addCartItem(user.getUserId(), menuItem.getMenuItemId(),
                cartItem.getQuantity());
        // Assert
        assertFalse(cartItems.isEmpty());
    }

    @Test
    public void testRemoveCartItem() {
        // Arrange
        cartService.addCartItem(user.getUserId(), menuItem.getMenuItemId(), cartItem.getQuantity());
        // Act
        List<CartItem> cartItems = cartService.removeCartItem(user.getUserId(), cartItem.getCartItemId());
        // Assert
        assertFalse(cartItems.contains(cartItem));
    }

    @Test
    public void testClearCart() {
        // Arrange
        cartService.addCartItem(user.getUserId(), menuItem.getMenuItemId(), cartItem.getQuantity());
        // Act
        cartService.clearCart(user.getUserId());
        // Assert
        assertTrue(cartService.findByUserId(user.getUserId()).isEmpty());
    }
}
