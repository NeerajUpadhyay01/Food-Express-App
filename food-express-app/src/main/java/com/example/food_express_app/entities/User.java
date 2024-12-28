package com.example.food_express_app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String name;
    private String password;
    private String contact;
    private String address;
    private boolean active = true;

    // @OneToMany(mappedBy = "user")
    // private List<Order> orderHistory;

    @PrePersist
    public void generateUserId() {
        this.userId = "USER" + System.currentTimeMillis();
    }
}
