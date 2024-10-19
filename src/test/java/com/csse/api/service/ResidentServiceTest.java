package com.csse.api.service;

import com.csse.api.dto.resident.ResidentRequestDTO;
import com.csse.api.dto.resident.ResidentResponseDTO;
import com.csse.api.exception.ResidentNotFoundException;
import com.csse.api.model.Resident;
import com.csse.api.repository.ResidentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ResidentServiceTest {

    @InjectMocks
    private ResidentService residentService;

    @Mock
    private ResidentRepository residentRepository;

    private Resident resident;
    private ResidentRequestDTO residentRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resident = new Resident();
        resident.setId(1L);
        resident.setName("John Doe");
        resident.setAddress("123 Main St");
        resident.setResidentialType("Apartment");

        residentRequestDTO = new ResidentRequestDTO();
        residentRequestDTO.setName("John Doe");
        residentRequestDTO.setAddress("123 Main St");
        residentRequestDTO.setResidentialType("Apartment");
    }

    @Test
    void create_ShouldReturnResidentResponseDTO() {
        when(residentRepository.save(any(Resident.class))).thenReturn(resident);

        ResidentResponseDTO response = residentService.create(residentRequestDTO);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
    }

    @Test
    void getById_ShouldReturnResidentResponseDTO_WhenResidentExists() {
        when(residentRepository.findById(1L)).thenReturn(Optional.of(resident));

        ResidentResponseDTO response = residentService.getById(1L);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
    }

    @Test
    void getById_ShouldThrowResidentNotFoundException_WhenResidentDoesNotExist() {
        when(residentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResidentNotFoundException.class, () -> residentService.getById(1L));
    }

    @Test
    void update_ShouldReturnUpdatedResidentResponseDTO() {
        when(residentRepository.findById(1L)).thenReturn(Optional.of(resident));
        when(residentRepository.save(any(Resident.class))).thenReturn(resident);

        ResidentResponseDTO response = residentService.update(1L, residentRequestDTO);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
    }

    @Test
    void delete_ShouldDeleteResident_WhenExists() {
        when(residentRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> residentService.delete(1L));
        verify(residentRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_ShouldThrowResidentNotFoundException_WhenDoesNotExist() {
        when(residentRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResidentNotFoundException.class, () -> residentService.delete(1L));
    }
}
