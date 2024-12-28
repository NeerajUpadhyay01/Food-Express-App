package com.example.food_express_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.food_express_app.entities.User;
import com.example.food_express_app.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;

    public User login(String name, String password) {
        return userRepository.findByNameAndPassword(name, password)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }

    public User registerUser(User user) {
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        return userRepository.save(user);
    }

    public User updateProfile(User user) {
        User existingUser = userRepository.findByUserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(user.getName());
        existingUser.setAddress(user.getAddress());
        existingUser.setContact(user.getContact());
        existingUser.setPassword(user.getPassword());
        existingUser.setActive(user.isActive());
        return userRepository.save(existingUser);
    }

    public void deactivateAccount(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void logout() {
        session.invalidate();
    }
}