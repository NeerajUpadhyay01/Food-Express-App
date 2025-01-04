package com.example.food_express_app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.food_express_app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    Optional<User> findByNameAndPassword(String name, String password);

    Optional<User> findByName(String name);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

}