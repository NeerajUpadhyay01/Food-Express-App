package com.example.food_express_app.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.food_express_app.entities.Admin;
import com.example.food_express_app.repositories.AdminRepository;

import java.util.Optional;

@SpringBootTest
public class AdminServiceTests {

    @Autowired
    private AdminService adminService;

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
    public void testCreateAdmin() {
        Admin createdAdmin = adminService.createAdmin(admin, 1L);
        assertNotNull(createdAdmin);
        assertEquals(admin.getEmail(), createdAdmin.getEmail());
    }

    @Test
    public void testGetAdminById() {
        adminRepository.save(admin);
        Optional<Admin> foundAdmin = adminService.getAdminById(admin.getAdminId());
        assertTrue(foundAdmin.isPresent());
        assertEquals(admin.getName(), foundAdmin.get().getName());
    }

    @Test
    public void testUpdateAdmin() {
        adminRepository.save(admin);
        admin.setName("Updated Admin");
        Admin updatedAdmin = adminService.updateAdmin(admin);
        assertEquals("Updated Admin", updatedAdmin.getName());
    }

    @Test
    public void testDeleteAdmin() {
        adminRepository.save(admin);
        adminService.deleteAdmin(admin.getAdminId(), 1L);
        assertFalse(adminRepository.findById(admin.getAdminId()).isPresent());
    }
}
