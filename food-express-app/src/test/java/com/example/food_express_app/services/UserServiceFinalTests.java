package com.example.food_express_app.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.food_express_app.entities.User;
import com.example.food_express_app.repositories.UserRepository;

@SpringBootTest
public class UserServiceFinalTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail("user@example.com");
        user.setPassword("newPassword");
        user.setName("Test User");
    }

    @Test
    public void testRegisterUser() {
        User registeredUser = userService.registerUser(user);
        assertNotNull(registeredUser);
        assertEquals(user.getEmail(), registeredUser.getEmail());
    }

    @Test
    public void testLogin() {
        userRepository.save(user);
        User loggedInUser = userService.login(user.getEmail(), user.getPassword());
        assertNotNull(loggedInUser);
        assertEquals(user.getName(), loggedInUser.getName());
    }

    @Test
    public void testUpdateProfile() {
        userRepository.save(user);
        user.setName("Updated User");
        User updatedUser = userService.updateProfile(user);
        assertEquals("Updated User", updatedUser.getName());
    }

    @Test
    public void testChangePassword() {
        userRepository.save(user);
        userService.changePassword(user.getUserId(), "newPassword", "newPassword123");
        User updatedUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        assertEquals("newPassword123", updatedUser.getPassword());
    }

    @Test
    public void testDeleteUser() {
        userRepository.save(user);
        userService.deleteUser(user.getUserId());
        assertFalse(userRepository.findById(user.getUserId()).isPresent());
    }
}
