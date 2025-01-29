package com.example.food_express_app.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.food_express_app.entities.Admin;

import java.util.Optional;

@DataJdbcTest
@ActiveProfiles("test")
public class AdminRepositoryTests {

    @Autowired
    private AdminRepository adminRepository;

    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin();
        admin.setEmail("admin@example.com");
        admin.setPassword("password");
        admin.setName("Admin User");
    }

    @Test
    public void testFindByEmail() {
        adminRepository.save(admin);
        Optional<Admin> foundAdmin = adminRepository.findByEmail("admin@example.com");
        assertTrue(foundAdmin.isPresent());
        assertEquals(admin.getName(), foundAdmin.get().getName());
    }
}
