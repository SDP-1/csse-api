package com.csse.api.controller;

import com.csse.api.dto.bin_types.BinTypesRequestDTO;
import com.csse.api.dto.bin_types.BinTypesResponseDTO;
import com.csse.api.exception.BinTypeNotFoundException;
import com.csse.api.service.BinTypesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BinTypesControllerTest {

    @InjectMocks
    private BinTypesController controller;

    @Mock
    private BinTypesService service;

    private BinTypesResponseDTO binTypeResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        binTypeResponse = new BinTypesResponseDTO(1L, "Recycling Bin", "50L", "EcoCorp", "Plastic");
    }

    @Test
    void create_ShouldReturnCreatedBinType() {
        BinTypesRequestDTO requestDTO = new BinTypesRequestDTO("Recycling Bin", "50L", "EcoCorp", "Plastic");
        when(service.create(requestDTO)).thenReturn(binTypeResponse);

        ResponseEntity<BinTypesResponseDTO> response = controller.create(requestDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(binTypeResponse, response.getBody());
    }

    @Test
    void getAll_ShouldReturnListOfBinTypes() {
        when(service.getAll()).thenReturn(Arrays.asList(binTypeResponse));

        ResponseEntity<List<BinTypesResponseDTO>> response = controller.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getById_ShouldReturnBinType() {
        when(service.getById(1L)).thenReturn(binTypeResponse);

        ResponseEntity<BinTypesResponseDTO> response = controller.getById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(binTypeResponse, response.getBody());
    }

    @Test
    void getById_ShouldThrowException_WhenNotFound() {
        when(service.getById(1L)).thenThrow(new BinTypeNotFoundException("BinType not found with id 1"));

        assertThrows(BinTypeNotFoundException.class, () -> controller.getById(1L));
    }

    @Test
    void update_ShouldReturnUpdatedBinType() {
        BinTypesRequestDTO requestDTO = new BinTypesRequestDTO("Updated Bin", "70L", "NewCorp", "Metal");
        when(service.update(1L, requestDTO)).thenReturn(binTypeResponse);

        ResponseEntity<BinTypesResponseDTO> response = controller.update(1L, requestDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(binTypeResponse, response.getBody());
    }

    @Test
    void update_ShouldThrowException_WhenNotFound() {
        BinTypesRequestDTO requestDTO = new BinTypesRequestDTO("Updated Bin", "70L", "NewCorp", "Metal");
        when(service.update(1L, requestDTO)).thenThrow(new BinTypeNotFoundException("BinType not found with id 1"));

        assertThrows(BinTypeNotFoundException.class, () -> controller.update(1L, requestDTO));
    }

    @Test
    void delete_ShouldReturnNoContent() {
        doNothing().when(service).delete(1L);

        ResponseEntity<Void> response = controller.delete(1L);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void delete_ShouldThrowException_WhenNotFound() {
        doThrow(new BinTypeNotFoundException("BinType not found with id 1")).when(service).delete(1L);

        assertThrows(BinTypeNotFoundException.class, () -> controller.delete(1L));
    }
}
