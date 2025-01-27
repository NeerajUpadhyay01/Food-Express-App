package com.example.food_express_app.controllers;

import com.example.food_express_app.entities.CartItem;
import com.example.food_express_app.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/items/{menuItemId}/{userId}")
    public ResponseEntity<List<CartItem>> addCartItem(@PathVariable Long userId, @PathVariable Long menuItemId,
            @RequestParam int quantity) {
        List<CartItem> cartItems = cartService.addCartItem(userId, menuItemId, quantity);
        return ResponseEntity.ok().body(cartItems);
    }

    @GetMapping("/items/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId) {
        List<CartItem> cartItems = cartService.findByUserId(userId);
        return ResponseEntity.ok().body(cartItems);
    }

    @PatchMapping("/items/{userId}/{cartItemId}/increase")
    public ResponseEntity<List<CartItem>> increaseCartItemQuantity(@PathVariable Long userId,
            @PathVariable Long cartItemId) {
        List<CartItem> cartItems = cartService.increaseCartItemQuantity(userId, cartItemId);
        return ResponseEntity.ok().body(cartItems);
    }

    @PatchMapping("/items/{userId}/{cartItemId}/decrease")
    public ResponseEntity<List<CartItem>> decreaseCartItemQuantity(@PathVariable Long userId,
            @PathVariable Long cartItemId) {
        List<CartItem> cartItems = cartService.decreaseCartItemQuantity(userId, cartItemId);
        return ResponseEntity.ok().body(cartItems);
    }

    @DeleteMapping("/items/{userId}/{cartItemId}")
    public ResponseEntity<List<CartItem>> removeCartItem(@PathVariable Long userId, @PathVariable Long cartItemId) {
        List<CartItem> cartItems = cartService.removeCartItem(userId, cartItemId);
        return ResponseEntity.ok().body(cartItems);
    }

    @DeleteMapping("/items/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}