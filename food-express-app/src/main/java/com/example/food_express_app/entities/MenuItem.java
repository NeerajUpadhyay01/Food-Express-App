package com.example.food_express_app.entities;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String description;
    private BigDecimal price;
    private Integer preparationTime;
    private Integer stockQuantity;
    private boolean available;

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setStockQuantity(int quantity) {
        this.stockQuantity = quantity;
    }

    // Getters, setters, constructors
}