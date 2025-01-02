package com.example.food_express_app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.food_express_app.entities.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategory(String category);

    List<MenuItem> findByAvailableAndStockQuantityGreaterThan(boolean available, int quantity);
}
