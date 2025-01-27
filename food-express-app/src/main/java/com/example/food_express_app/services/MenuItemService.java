package com.example.food_express_app.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food_express_app.repositories.MenuItemRepository;
import com.example.food_express_app.repositories.RestaurantRepository;

import lombok.RequiredArgsConstructor;

import com.example.food_express_app.entities.MenuItem;
import com.example.food_express_app.entities.Restaurant;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<MenuItem> getMenuItemsByRestaurantId(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return menuItemRepository.findAllByRestaurant(restaurant.get());
        } else {
            throw new RuntimeException("Restaurant not found");
        }
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
    }

    public MenuItem addMenuItem(Long restaurantId, MenuItem item) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        if (item == null) {
            throw new IllegalArgumentException("Menu item cannot be null");
        }

        item.setRestaurant(restaurant);
        return menuItemRepository.save(item);
    }

    public void removeMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        if (menuItem.getImage() != null) {
            String publicId = cloudinaryService.getPublicIdFromUrl(menuItem.getImage());
            try {
                cloudinaryService.deleteFile(publicId);
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete image from Cloudinary", e);
            }
        }

        menuItemRepository.delete(menuItem);
    }

    public MenuItem updateMenuItem(MenuItem item, String imageUrl) {
        MenuItem existingItem = menuItemRepository.findById(item.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        BeanUtils.copyProperties(item, existingItem, "menuItemId", "restaurant", "image");
        existingItem.setImage(imageUrl);
        return menuItemRepository.save(existingItem);
    }

    public void updateStock(Long id, int quantity, boolean available) {
        if (id == null) {
            throw new IllegalArgumentException("Menu item ID cannot be null");
        }

        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
        item.setStockQuantity(quantity);
        item.setAvailable(available);
        menuItemRepository.save(item);
    }
}
