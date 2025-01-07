package com.example.food_express_app.entities;

import java.math.BigDecimal;
import java.util.function.IntPredicate;

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

    public void setPrice(double d) {
        this.price = BigDecimal.valueOf(d);
    }

	public void setPreparationTime(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setDescription(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setCategory(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getStockQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	public IntPredicate getCategory() {
		// TODO Auto-generated method stub
		return null;
	}

    // Getters, setters, constructors
}