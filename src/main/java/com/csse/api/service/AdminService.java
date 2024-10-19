package com.csse.api.service;

import com.csse.api.dto.admin.AdminRequestDTO;
import com.csse.api.dto.admin.AdminResponseDTO;
import com.csse.api.model.Admin;
import com.csse.api.repository.AdminRepository;
import com.csse.api.exception.AdminNotFoundException;
import com.csse.api.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminResponseDTO createAdmin(AdminRequestDTO adminRequestDTO) {
        Admin admin = new Admin();
        admin.setName(adminRequestDTO.getName());
        admin.setSuperAdmin(adminRequestDTO.isSuperAdmin());
        // Default values for email, password, and userType
        admin.setEmail("default@example.com");
        admin.setPassword("defaultPassword");
        admin.setUserType(UserType.ADMIN);

        Admin savedAdmin = adminRepository.save(admin);
        return convertToResponseDTO(savedAdmin);
    }

    public List<AdminResponseDTO> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public Optional<AdminResponseDTO> getAdminById(Long id) {
        return adminRepository.findById(id)
                .map(this::convertToResponseDTO);
    }

    public AdminResponseDTO updateAdmin(Long id, AdminRequestDTO adminRequestDTO) {
        Admin adminToUpdate = adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin with id " + id + " not found"));

        adminToUpdate.setName(adminRequestDTO.getName());
        adminToUpdate.setSuperAdmin(adminRequestDTO.isSuperAdmin());

        Admin updatedAdmin = adminRepository.save(adminToUpdate);
        return convertToResponseDTO(updatedAdmin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    private AdminResponseDTO convertToResponseDTO(Admin admin) {
        return new AdminResponseDTO(admin.getId(), admin.getName(), admin.isSuperAdmin());
    }
}
