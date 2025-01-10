package com.example.food_express_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.food_express_app.entities.Admin;
import com.example.food_express_app.services.AdminService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Admin>> getAdminById(@PathVariable Long id) {
        Optional<Admin> admin = adminService.getAdminById(id);
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/{createdByAdminId}")
    public ResponseEntity<Admin> createAdmin(@ModelAttribute Admin admin, @PathVariable Long createdByAdminId) {
        Admin createdAdmin = adminService.createAdmin(admin, createdByAdminId);
        return ResponseEntity.ok(createdAdmin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @ModelAttribute Admin adminDetails) {
        adminDetails.setAdmin_id(id);
        Admin updatedAdmin = adminService.updateAdmin(adminDetails);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{id}/{requestingAdminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id, @PathVariable Long requestingAdminId) {
        adminService.deleteAdmin(id, requestingAdminId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@ModelAttribute Admin adminDetails) {
        Optional<Admin> admin = adminService.loginAdmin(adminDetails.getEmail(), adminDetails.getPassword());
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
