package com.csse.api.controller;

import com.csse.api.dto.vehicleType.VehicleTypeRequirementRequestDTO;
import com.csse.api.dto.vehicleType.VehicleTypeRequirementResponseDTO;
import com.csse.api.exception.VehicleTypeRequirementNotFoundException;
import com.csse.api.service.VehicleTypeRequirementService;
import com.csse.api.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleTypeRequirementControllerTest {

    @InjectMocks
    private VehicleTypeRequirementController controller;

    @Mock
    private VehicleTypeRequirementService service;

    private VehicleTypeRequirementResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        responseDTO = new VehicleTypeRequirementResponseDTO(1L, VehicleType.SM, 2, null);
    }

    @Test
    void createVehicleTypeRequirement_ShouldReturnCreatedResponse() {
        VehicleTypeRequirementRequestDTO requestDTO = new VehicleTypeRequirementRequestDTO(VehicleType.SM, 2, null);
        when(service.createVehicleTypeRequirement(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<VehicleTypeRequirementResponseDTO> response = controller.createVehicleTypeRequirement(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void getVehicleTypeRequirementById_ShouldReturnOkResponse_WhenFound() {
        when(service.getVehicleTypeRequirementById(1L)).thenReturn(responseDTO);

        ResponseEntity<VehicleTypeRequirementResponseDTO> response = controller.getVehicleTypeRequirementById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void getVehicleTypeRequirementById_ShouldReturnNotFoundResponse_WhenNotFound() {
        // Arrange
        when(service.getVehicleTypeRequirementById(1L)).thenThrow(new VehicleTypeRequirementNotFoundException("VehicleTypeRequirement not found with id: 1"));

        // Act
        ResponseEntity<VehicleTypeRequirementResponseDTO> response = controller.getVehicleTypeRequirementById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllVehicleTypeRequirements_ShouldReturnOkResponse() {
        List<VehicleTypeRequirementResponseDTO> responseDTOs = List.of(responseDTO);
        when(service.getAllVehicleTypeRequirements()).thenReturn(responseDTOs);

        ResponseEntity<List<VehicleTypeRequirementResponseDTO>> response = controller.getAllVehicleTypeRequirements();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTOs, response.getBody());
    }

    @Test
    void updateVehicleTypeRequirement_ShouldReturnOkResponse() {
        VehicleTypeRequirementRequestDTO requestDTO = new VehicleTypeRequirementRequestDTO(VehicleType.SM, 3, null);
        when(service.updateVehicleTypeRequirement(1L, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<VehicleTypeRequirementResponseDTO> response = controller.updateVehicleTypeRequirement(1L, requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void deleteVehicleTypeRequirement_ShouldReturnNoContentResponse() {
        doNothing().when(service).deleteVehicleTypeRequirement(1L);

        ResponseEntity<Void> response = controller.deleteVehicleTypeRequirement(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
