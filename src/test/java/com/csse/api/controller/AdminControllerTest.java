package com.csse.api.controller;

import com.csse.api.dto.admin.AdminRequestDTO;
import com.csse.api.dto.admin.AdminResponseDTO;
import com.csse.api.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    private AdminRequestDTO adminRequestDTO;
    private AdminResponseDTO adminResponseDTO;

    @BeforeEach
    public void setUp() {
        adminRequestDTO = new AdminRequestDTO("John Doe", false);
        adminResponseDTO = new AdminResponseDTO(1L, "John Doe", false);
    }

    @Test
    public void createAdmin_ShouldReturnCreatedAdminResponseDTO() {
        when(adminService.createAdmin(adminRequestDTO)).thenReturn(adminResponseDTO);

        ResponseEntity<AdminResponseDTO> response = adminController.createAdmin(adminRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(adminResponseDTO, response.getBody());
    }

    @Test
    public void getAllAdmins_ShouldReturnListOfAdmins() {
        when(adminService.getAllAdmins()).thenReturn(List.of(adminResponseDTO));

        ResponseEntity<List<AdminResponseDTO>> response = adminController.getAllAdmins();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void getAdminById_ShouldReturnAdminResponseDTO() {
        when(adminService.getAdminById(1L)).thenReturn(Optional.of(adminResponseDTO));

        ResponseEntity<AdminResponseDTO> response = adminController.getAdminById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adminResponseDTO, response.getBody());
    }

    @Test
    public void getAdminById_ShouldReturnNotFound() {
        when(adminService.getAdminById(1L)).thenReturn(Optional.empty());

        ResponseEntity<AdminResponseDTO> response = adminController.getAdminById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
