package com.example.food_express_app.services;

import com.example.food_express_app.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    private User newUser;

    @BeforeEach
    void setUp() {
        // Initialize test data if necessary
        newUser = new User();
        newUser.setUsername("newUser");
        newUser.setPassword("newPassword");
        newUser.setContact("1234567890");
        newUser.setAddress("Mexico City");
        newUser.setActive(true);
    }

    @Test
    void testLogin() {
        userService.registerUser(newUser);
        User result = userService.login("newUser", "newPassword");
        assertNotNull(result);
    }

    @Test
    void testRegisterUser() {
        User result = userService.registerUser(newUser);
        assertNotNull(result);
    }

    @Test
    void testUpdateProfile() {
        // Register the user before attempting to update the profile
        userService.registerUser(newUser);
        newUser.setContact("newPhone");
        newUser.setAddress("newAddress");
        User result = userService.updateProfile(newUser);
        assertNotNull(result);
        assertEquals("newPhone", result.getContact());
        assertEquals("newAddress", result.getAddress());
    }

    @Test
    void testChangePassword() {
        // Register the user before attempting to change the password
        userService.registerUser(newUser);
        userService.changePassword(newUser.getUserId(), "newPassword", "newPassword123");
        User result = userService.login("newUser", "newPassword123");
        assertNotNull(result);
    }

    @Test
    void testToggleAccountStatus() {
        // Register the user before attempting to toggle the account status
        userService.registerUser(newUser);
        boolean initialStatus = newUser.isActive();
        userService.toggleAccountStatus(newUser.getUserId());
        Optional<User> updatedUser = userService.findByUserId(newUser.getUserId());
        assertNotNull(updatedUser);
        assertNotEquals(initialStatus, updatedUser.get().isActive());
    }

    @Test
    void testGetAllUsers() {
        userService.registerUser(newUser);
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    void testLogout() {
        // Register and log in the user before attempting to log out
        userService.registerUser(newUser);
        userService.login("newUser", "newPassword");
        userService.logout();
        assertTrue(true);
    }
}
