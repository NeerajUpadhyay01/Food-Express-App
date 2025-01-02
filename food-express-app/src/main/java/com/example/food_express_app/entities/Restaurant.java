package com.example.food_express_app.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String contact;

    @Column(name = "open_time")
    private String openTime;

    @Column(name = "close_time")
    private String closeTime;

    @Column(nullable = false)
    private String password;

}