package com.example.food_express_app.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
@JsonIgnoreProperties({ "order" })
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    private MenuItem menuItem;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonBackReference
    private Order order;

    private int quantity;

    private double price;

    public Long getMenuItemId() {
        return menuItem.getMenuItemId();
    }

    public Long getUserId() {
        return user.getUserId();
    }
}
