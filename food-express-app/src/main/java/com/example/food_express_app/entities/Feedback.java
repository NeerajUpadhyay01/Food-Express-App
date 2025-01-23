package com.example.food_express_app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "feedbacks")
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Order order;

    private String review;
    private int rating;
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}
