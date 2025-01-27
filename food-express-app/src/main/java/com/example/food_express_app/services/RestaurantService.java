package com.example.food_express_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.food_express_app.entities.Restaurant;
import com.example.food_express_app.repositories.RestaurantRepository;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Restaurant restaurant, String imageUrl) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(restaurant.getRestaurantId());
        if (existingRestaurant.isPresent()) {
            Restaurant updatedRestaurant = existingRestaurant.get();
            updatedRestaurant.setName(restaurant.getName());
            updatedRestaurant.setPassword(restaurant.getPassword());
            updatedRestaurant.setEmail(restaurant.getEmail());
            updatedRestaurant.setName(restaurant.getName());
            updatedRestaurant.setAddress(restaurant.getAddress());
            updatedRestaurant.setOpenTime(restaurant.getOpenTime());
            updatedRestaurant.setCloseTime(restaurant.getCloseTime());
            updatedRestaurant.setContact(restaurant.getContact());
            updatedRestaurant.setImage(imageUrl);

            return restaurantRepository.save(updatedRestaurant);
        } else {
            throw new RuntimeException("Restaurant not found");
        }
    }

    public void deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        if (restaurant.getImage() != null) {
            String publicId = cloudinaryService.getPublicIdFromUrl(restaurant.getImage());
            try {
                cloudinaryService.deleteFile(publicId);
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete image from Cloudinary", e);
            }
        }

        restaurantRepository.delete(restaurant);
    }

    public Restaurant loginRestaurant(String email, String password) {
        Restaurant restaurant = restaurantRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        if (restaurant.getPassword().equals(password)) {
            return restaurant;
        } else {
            throw new RuntimeException("Invalid password");
        }
    }

    public Restaurant toggleStatus(Long id) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(id);
        if (existingRestaurant.isPresent()) {
            Restaurant restaurant = existingRestaurant.get();
            restaurant.setOpen(!restaurant.isOpen());
            return restaurantRepository.save(restaurant);
        } else {
            throw new RuntimeException("Restaurant not found");
        }
    }
}
