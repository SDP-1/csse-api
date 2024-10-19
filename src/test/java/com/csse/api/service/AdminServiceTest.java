package com.csse.api.service;

import com.csse.api.dto.admin.AdminRequestDTO;
import com.csse.api.dto.admin.AdminResponseDTO;
import com.csse.api.exception.AdminNotFoundException;
import com.csse.api.model.Admin;
import com.csse.api.enums.UserType;
import com.csse.api.repository.AdminRepository;
import com.csse.api.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    private Admin admin;
    private AdminRequestDTO adminRequestDTO;

    @BeforeEach
    public void setUp() {
        admin = new Admin(1L, "test@example.com", "password", UserType.ADMIN, "John Doe", false);
        adminRequestDTO = new AdminRequestDTO("John Doe", false);
    }

    @Test
    public void createAdmin_ShouldReturnAdminResponseDTO() {
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);

        AdminResponseDTO response = adminService.createAdmin(adminRequestDTO);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        assertFalse(response.isSuperAdmin());
        verify(adminRepository).save(any(Admin.class));
    }

    @Test
    public void updateAdmin_ShouldReturnUpdatedAdminResponseDTO() {
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);

        AdminResponseDTO response = adminService.updateAdmin(1L, adminRequestDTO);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        assertFalse(response.isSuperAdmin());
        verify(adminRepository).save(any(Admin.class));
    }

    @Test
    public void updateAdmin_ShouldThrowAdminNotFoundException() {
        when(adminRepository.findById(1L)).thenReturn(Optional.empty());

        AdminNotFoundException exception = assertThrows(AdminNotFoundException.class, () -> {
            adminService.updateAdmin(1L, adminRequestDTO);
        });

        assertEquals("Admin with id 1 not found", exception.getMessage());
    }

    @Test
    public void getAllAdmins_ShouldReturnListOfAdmins() {
        when(adminRepository.findAll()).thenReturn(List.of(admin));

        List<AdminResponseDTO> response = adminService.getAllAdmins();

        assertEquals(1, response.size());
        assertEquals("John Doe", response.get(0).getName());
    }
}
