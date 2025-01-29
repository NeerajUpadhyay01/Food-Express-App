package com.example.food_express_app.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.food_express_app.entities.CartItem;

import java.util.List;

@DataJdbcTest
@ActiveProfiles("test")
public class CartItemRepositoryTests {

    @Autowired
    private CartItemRepository cartItemRepository;

    private CartItem cartItem;

    @BeforeEach
    public void setUp() {
        cartItem = new CartItem();
        cartItem.setQuantity(2);
        // Set other properties as needed
    }

    @Test
    public void testFindByUser_UserId() {
        // Assuming a user with ID 1 exists
        cartItemRepository.save(cartItem);
        List<CartItem> foundItems = cartItemRepository.findByUser_userId(1L);
        assertFalse(foundItems.isEmpty());
    }
}
