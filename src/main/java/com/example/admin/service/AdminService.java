package com.example.admin.service;

import com.example.admin.model.Admin;
import com.example.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    
    public boolean validateAdmin(Admin admin) {
        Admin existingAdmin = adminRepository.findByEmail(admin.getEmail());
        return existingAdmin != null && existingAdmin.getPassword().equals(admin.getPassword());
    }

    
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    
    public Admin updateAdmin(Long id, Admin adminDetails) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin existingAdmin = optionalAdmin.get();
            existingAdmin.setFirstName(adminDetails.getFirstName());
            existingAdmin.setLastName(adminDetails.getLastName());
            existingAdmin.setEmail(adminDetails.getEmail());
            existingAdmin.setPassword(adminDetails.getPassword());
            return adminRepository.save(existingAdmin);
        }
        return null; 
    }

    
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    
    public Optional<Admin> findAdminById(Long id) {
        return adminRepository.findById(id);
    }

    
    public Iterable<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }
}
