package com.example.food_express_app.services;

import com.example.food_express_app.entities.User;
import com.example.food_express_app.repositories.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    private User newUser;

    @BeforeEach
    void setUp() {
        // Clean up test data to avoid conflicts
        userRepository.deleteAll();

        // Initialize test data
        newUser = new User();
        newUser.setEmail("newUser@example.com");
        newUser.setPassword("newPassword");
        newUser.setContact("1234567890");
        newUser.setAddress("Mexico City");
        newUser.setActive(true);
        userService.registerUser(newUser);
    }

    @Test
    void testRegisterUser() {
        User result = newUser;
        assertNotNull(result);
    }

    @Test
    void testLogin() {
        User result = userService.login("newUser@example.com", "newPassword");
        assertNotNull(result);
    }

    @Test
    void testUpdateProfile() {
        newUser.setContact("newPhone");
        newUser.setAddress("newAddress");
        User result = userService.updateProfile(newUser);
        assertNotNull(result);
        assertEquals("newPhone", result.getContact());
        assertEquals("newAddress", result.getAddress());
    }

    @Test
    void testChangePassword() {
        userService.changePassword(newUser.getUserId(), "newPassword", "newPassword123");
        User result = userService.login("newUser@example.com", "newPassword123");
        assertNotNull(result);
    }

    @Test
    void testToggleAccountStatus() {
        boolean initialStatus = newUser.isActive();
        userService.toggleAccountStatus(newUser.getUserId());
        Optional<User> updatedUser = userService.findByUserId(newUser.getUserId());
        assertNotNull(updatedUser);
        assertNotEquals(initialStatus, updatedUser.get().isActive());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    void testLogout() {
        userService.login("newUser@example.com", "newPassword");
        userService.logout();
        assertTrue(true);
    }
}
