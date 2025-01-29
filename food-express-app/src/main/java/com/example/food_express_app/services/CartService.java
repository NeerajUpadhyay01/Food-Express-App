package com.example.food_express_app.services;

import com.example.food_express_app.entities.CartItem;
import com.example.food_express_app.entities.MenuItem;
import com.example.food_express_app.entities.User;
import com.example.food_express_app.repositories.CartItemRepository;
import com.example.food_express_app.repositories.MenuItemRepository;
import com.example.food_express_app.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CartItem> addCartItem(Long userId, Long menuItemId, int quantity) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartItem cartItem = new CartItem();
        cartItem.setMenuItem(menuItem);
        cartItem.setUser(user);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(menuItem.getPrice().multiply(BigDecimal.valueOf(quantity)).doubleValue());

        cartItemRepository.save(cartItem);
        return cartItemRepository.findByUser_userId(userId);
    }

    public List<CartItem> removeCartItem(Long userId, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));
        if (!cartItem.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized access");
        }
        cartItemRepository.deleteById(cartItemId);
        return cartItemRepository.findByUser_userId(userId);
    }

    public void clearCart(Long userId) {
        List<CartItem> cartItems = findByUserId(userId);
        if (!cartItems.isEmpty()) {
            cartItemRepository.deleteAll(cartItems);
        }
    }

    public List<CartItem> findByUserId(Long userId) {
        return cartItemRepository.findByUser_userId(userId);
    }

    public void updateCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public List<CartItem> increaseCartItemQuantity(Long userId, Long cartItemId) {
        CartItem cartItem = findByCartItemId(cartItemId);
        if (cartItem != null) {
            MenuItem menuItem = cartItem.getMenuItem();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setPrice(menuItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())).doubleValue());
            cartItemRepository.save(cartItem);
        }
        return findByUserId(userId);
    }

    public List<CartItem> decreaseCartItemQuantity(Long userId, Long cartItemId) {
        CartItem cartItem = findByCartItemId(cartItemId);
        if (cartItem != null && cartItem.getQuantity() > 1) {
            MenuItem menuItem = cartItem.getMenuItem();
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItem.setPrice(menuItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())).doubleValue());
            cartItemRepository.save(cartItem);
        } else if (cartItem != null && cartItem.getQuantity() == 1) {
            removeCartItem(userId, cartItemId);
        }
        return findByUserId(userId);
    }

    private CartItem findByCartItemId(Long cartItemId) {
        return cartItemRepository.findById(cartItemId).orElse(null);
    }
}