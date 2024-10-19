package com.csse.api.controller;

import com.csse.api.dto.wasteType.WasteTypeRequestDTO;
import com.csse.api.dto.wasteType.WasteTypeResponseDTO;
import com.csse.api.exception.WasteTypeNotFoundException;
import com.csse.api.service.WasteTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WasteTypeControllerTest {

    @InjectMocks
    private WasteTypeController wasteTypeController;

    @Mock
    private WasteTypeService wasteTypeService;

    private WasteTypeRequestDTO wasteTypeRequestDTO;
    private WasteTypeResponseDTO wasteTypeResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        wasteTypeRequestDTO = new WasteTypeRequestDTO("Test Waste", "Test Description", 10.0f, 5.0f, 15.0f);
        wasteTypeResponseDTO = new WasteTypeResponseDTO(1L, "Test Waste", "Test Description", 10.0f, 5.0f, 15.0f);
    }

    @Test
    void getAllWasteTypes_ShouldReturnListOfWasteTypes() {
        when(wasteTypeService.getAllWasteTypes()).thenReturn(new ResponseEntity<>(Collections.singletonList(wasteTypeResponseDTO), HttpStatus.OK));

        ResponseEntity<List<WasteTypeResponseDTO>> response = wasteTypeController.getAllWasteTypes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(wasteTypeResponseDTO, response.getBody().get(0));
    }

    @Test
    void getWasteTypeById_ShouldReturnWasteType_WhenFound() {
        when(wasteTypeService.getWasteTypeById(1L)).thenReturn(new ResponseEntity<>(wasteTypeResponseDTO, HttpStatus.OK));

        ResponseEntity<?> response = wasteTypeController.getWasteTypeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wasteTypeResponseDTO, response.getBody());
    }

    @Test
    void getWasteTypeById_ShouldReturnNotFound_WhenNotFound() {
        when(wasteTypeService.getWasteTypeById(1L)).thenThrow(new WasteTypeNotFoundException("Waste type not found with ID: 1"));

        ResponseEntity<?> response = wasteTypeController.getWasteTypeById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Waste type not found with ID: 1", response.getBody());
    }

    @Test
    void createWasteType_ShouldReturnCreatedWasteType() {
        when(wasteTypeService.createWasteType(any(WasteTypeRequestDTO.class))).thenReturn(new ResponseEntity<>(wasteTypeResponseDTO, HttpStatus.CREATED));

        ResponseEntity<WasteTypeResponseDTO> response = wasteTypeController.createWasteType(wasteTypeRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(wasteTypeResponseDTO, response.getBody());
    }

    @Test
    void updateWasteType_ShouldReturnUpdatedWasteType() {
        when(wasteTypeService.updateWasteType(1L, wasteTypeRequestDTO)).thenReturn(new ResponseEntity<>(wasteTypeResponseDTO, HttpStatus.OK));

        ResponseEntity<WasteTypeResponseDTO> response = wasteTypeController.updateWasteType(1L, wasteTypeRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wasteTypeResponseDTO, response.getBody());
    }

    @Test
    void updateWasteType_ShouldReturnNotFound_WhenNotFound() {
        // Setup mock to throw exception for non-existing ID
        when(wasteTypeService.updateWasteType(1L, wasteTypeRequestDTO)).thenThrow(new WasteTypeNotFoundException("Waste type not found with ID: 1"));

        // Call the controller method
        ResponseEntity<WasteTypeResponseDTO> response = wasteTypeController.updateWasteType(1L, wasteTypeRequestDTO);

        // Assert the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Waste type not found with ID: 1", response.getBody());
    }

    @Test
    void deleteWasteType_ShouldReturnNoContent_WhenDeleted() {
        when(wasteTypeService.deleteWasteType(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        ResponseEntity<Void> response = wasteTypeController.deleteWasteType(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteWasteType_ShouldReturnNotFound_WhenNotFound() {
        when(wasteTypeService.deleteWasteType(1L)).thenThrow(new WasteTypeNotFoundException("Waste type not found with ID: 1"));

        ResponseEntity<Void> response = wasteTypeController.deleteWasteType(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Waste type not found with ID: 1", response.getBody());
    }
}
