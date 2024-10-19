package com.csse.api.controller;

import com.csse.api.dto.resident.ResidentRequestDTO;
import com.csse.api.dto.resident.ResidentResponseDTO;
import com.csse.api.exception.ResidentNotFoundException;
import com.csse.api.service.ResidentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ResidentControllerTest {

    @InjectMocks
    private ResidentController residentController;

    @Mock
    private ResidentService residentService;

    private ResidentRequestDTO residentRequestDTO;
    private ResidentResponseDTO residentResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        residentRequestDTO = new ResidentRequestDTO();
        residentRequestDTO.setName("John Doe");
        residentRequestDTO.setAddress("123 Main St");
        residentRequestDTO.setResidentialType("Apartment");

        residentResponseDTO = new ResidentResponseDTO();
        residentResponseDTO.setId(1L);
        residentResponseDTO.setName("John Doe");
    }

    @Test
    void create_ShouldReturnResidentResponseDTO() {
        when(residentService.create(any(ResidentRequestDTO.class))).thenReturn(residentResponseDTO);

        ResponseEntity<ResidentResponseDTO> response = residentController.create(residentRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void getAll_ShouldReturnListOfResidentResponseDTOs() {
        when(residentService.getAll()).thenReturn(Collections.singletonList(residentResponseDTO));

        ResponseEntity<List<ResidentResponseDTO>> response = residentController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void getById_ShouldReturnResidentResponseDTO_WhenResidentExists() {
        when(residentService.getById(1L)).thenReturn(residentResponseDTO);

        ResponseEntity<ResidentResponseDTO> response = residentController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void getById_ShouldReturnNotFound_WhenResidentDoesNotExist() {
        when(residentService.getById(1L)).thenThrow(new ResidentNotFoundException("Resident not found with ID: 1"));

        ResponseEntity<ResidentResponseDTO> response = residentController.getById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void update_ShouldReturnUpdatedResidentResponseDTO() {
        when(residentService.update(anyLong(), any(ResidentRequestDTO.class))).thenReturn(residentResponseDTO);

        ResponseEntity<ResidentResponseDTO> response = residentController.update(1L, residentRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getName());
    }

    @Test
    void update_ShouldReturnNotFound_WhenResidentDoesNotExist() {
        when(residentService.update(anyLong(), any(ResidentRequestDTO.class)))
                .thenThrow(new ResidentNotFoundException("Resident not found with ID: 1"));

        ResponseEntity<ResidentResponseDTO> response = residentController.update(1L, residentRequestDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void delete_ShouldReturnNoContent_WhenDeleted() {
        doNothing().when(residentService).delete(1L);

        ResponseEntity<Void> response = residentController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void delete_ShouldReturnNotFound_WhenResidentDoesNotExist() {
        doThrow(new ResidentNotFoundException("Resident not found with ID: 1")).when(residentService).delete(1L);

        ResponseEntity<Void> response = residentController.delete(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
