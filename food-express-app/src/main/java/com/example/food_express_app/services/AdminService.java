package com.example.food_express_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.food_express_app.entities.Admin;
import com.example.food_express_app.repositories.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public Admin createAdmin(Admin admin, Long createdByAdminId) {
        admin.setCreatedBy(createdByAdminId);
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Admin admin) {
        Optional<Admin> existingAdmin = adminRepository.findById(admin.getAdmin_id());
        if (existingAdmin.isPresent()) {
            Admin updatedAdmin = existingAdmin.get();
            updatedAdmin.setName(admin.getName());
            updatedAdmin.setContact(admin.getContact());
            updatedAdmin.setEmail(admin.getEmail());
            updatedAdmin.setPassword(admin.getPassword());
            return adminRepository.save(updatedAdmin);
        } else {
            throw new RuntimeException("Admin not found");
        }
    }

    public void deleteAdmin(Long id, Long requestingAdminId) {
        Optional<Admin> adminToDelete = adminRepository.findById(id);
        if (adminToDelete.isPresent()) {
            if (adminToDelete.get().getCreatedBy().equals(requestingAdminId)) {
                adminRepository.deleteById(id);
            } else {
                throw new RuntimeException("You do not have permission to delete this admin");
            }
        } else {
            throw new RuntimeException("Admin not found");
        }
    }

    public Optional<Admin> loginAdmin(String email, String password) {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            return admin;
        } else {
            return Optional.empty();
        }
    }
}
