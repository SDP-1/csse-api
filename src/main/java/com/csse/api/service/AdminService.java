package com.csse.api.service;

import com.csse.api.dto.admin.AdminRequestDTO;
import com.csse.api.dto.admin.AdminResponseDTO;
import com.csse.api.model.Admin;
import com.csse.api.repository.AdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminService(AdminRepository adminRepository, ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
    }

    public AdminResponseDTO createAdmin(AdminRequestDTO adminRequestDTO) {
        Admin admin = modelMapper.map(adminRequestDTO, Admin.class);
        Admin savedAdmin = adminRepository.save(admin);
        return modelMapper.map(savedAdmin, AdminResponseDTO.class);
    }

    public List<AdminResponseDTO> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(admin -> modelMapper.map(admin, AdminResponseDTO.class))
                .toList();
    }

    public Optional<AdminResponseDTO> getAdminById(Long id) {
        return adminRepository.findById(id)
                .map(admin -> modelMapper.map(admin, AdminResponseDTO.class));
    }

    public AdminResponseDTO updateAdmin(Long id, AdminRequestDTO adminRequestDTO) {
        Admin adminToUpdate = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        // Update fields
        adminToUpdate.setName(adminRequestDTO.getName());
        adminToUpdate.setSuperAdmin(adminRequestDTO.isSuperAdmin());

        Admin updatedAdmin = adminRepository.save(adminToUpdate);
        return modelMapper.map(updatedAdmin, AdminResponseDTO.class);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
