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
    private MenuItemRepository repository;

    public List<MenuItem> getAvailableItems() {
        return repository.findByAvailableAndStockQuantityGreaterThan(true, 0);
    }

    public MenuItem addMenuItem(MenuItem item) {
        return repository.save(item);
    }

    public void removeMenuItem(Long id) {
        repository.deleteById(id);
    }

    public MenuItem updateMenuItem(Long id, MenuItem item) {
        MenuItem existingItem = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        BeanUtils.copyProperties(item, existingItem, "id");
        return repository.save(existingItem);
    }

    public void updateStock(Long id, int quantity, boolean available) {
        MenuItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
        item.setStockQuantity(quantity);
        item.setAvailable(available);
        repository.save(item);
    }
}
