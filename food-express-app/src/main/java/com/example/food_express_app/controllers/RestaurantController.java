package com.example.food_express_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.food_express_app.entities.Restaurant;
import com.example.food_express_app.entities.Feedback;
import com.example.food_express_app.services.CloudinaryService;
import com.example.food_express_app.services.RestaurantService;

import lombok.RequiredArgsConstructor;

import com.example.food_express_app.services.FeedbackService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "restaurant/register";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "restaurant/login";
    }

    @GetMapping("/profile/update/{id}")
    public String showEditProfileForm(@PathVariable Long id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        model.addAttribute("restaurant", restaurant);
        return "restaurant/update_profile";
    }

    @GetMapping("/details/{id}")
    public String getRestaurantDetails(@PathVariable Long id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        List<Feedback> feedbacks = feedbackService.getFeedbackByRestaurant(id);
        double averageRating = feedbackService.getAverageRating(id);

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("newFeedback", new Feedback());

        return "restaurant/details";
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Restaurant createRestaurant(@ModelAttribute Restaurant restaurant,
            @RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = cloudinaryService.uploadFile(file);
            restaurant.setImage(imageUrl);
            return restaurantService.createRestaurant(restaurant);
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }

    @PostMapping("/toggleStatus/{id}")
    public Restaurant toggleStatus(@PathVariable Long id) {
        return restaurantService.toggleStatus(id);
    }

    @PostMapping("/login")
    public Restaurant loginRestaurant(@RequestParam String email, @RequestParam String password) {
        return restaurantService.loginRestaurant(email, password);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Restaurant updateRestaurant(@PathVariable Long id, @ModelAttribute Restaurant restaurant,
            @ModelAttribute("file") MultipartFile file) {
        try {
            Restaurant existingRestaurant = restaurantService.getRestaurantById(id)
                    .orElseThrow(() -> new RuntimeException("Restaurant not found"));
            if (existingRestaurant.getImage() != null) {
                String publicId = cloudinaryService.getPublicIdFromUrl(existingRestaurant.getImage());
                cloudinaryService.deleteFile(publicId);
            }
            String imageUrl = cloudinaryService.uploadFile(file);
            restaurant.setRestaurantId(id);
            return restaurantService.updateRestaurant(restaurant, imageUrl);
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok().build();
    }
}
