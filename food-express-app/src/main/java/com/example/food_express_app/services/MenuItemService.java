package com.example.food_express_app.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food_express_app.repositories.MenuItemRepository;
import com.example.food_express_app.entities.MenuItem;

@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItem> getAvailableItems() {
        return menuItemRepository.findAllByAvailable(true);
    }

    public MenuItem addMenuItem(MenuItem item) {
        return menuItemRepository.save(item);
    }

    public void removeMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    public MenuItem updateMenuItem(Long id, MenuItem item) {
        MenuItem existingItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        BeanUtils.copyProperties(item, existingItem, "id");
        return menuItemRepository.save(existingItem);
    }

    public void updateStock(Long id, int quantity, boolean available) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
        item.setStockQuantity(quantity);
        item.setAvailable(available);
        menuItemRepository.save(item);
    }
}
