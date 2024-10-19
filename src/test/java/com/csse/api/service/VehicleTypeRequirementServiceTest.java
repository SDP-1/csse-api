package com.csse.api.service;

import com.csse.api.dto.vehicleType.VehicleTypeRequirementRequestDTO;
import com.csse.api.dto.vehicleType.VehicleTypeRequirementResponseDTO;
import com.csse.api.exception.VehicleTypeRequirementNotFoundException;
import com.csse.api.model.VehicleTypeRequirement;
import com.csse.api.repository.VehicleTypeRequirementRepository;
import com.csse.api.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleTypeRequirementServiceTest {

    @InjectMocks
    private VehicleTypeRequirementService service;

    @Mock
    private VehicleTypeRequirementRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private VehicleTypeRequirement vehicleTypeRequirement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehicleTypeRequirement = new VehicleTypeRequirement(1L, VehicleType.SM, 2, null);
    }

    @Test
    void createVehicleTypeRequirement_ShouldReturnResponseDTO() {
        VehicleTypeRequirementRequestDTO requestDTO = new VehicleTypeRequirementRequestDTO(VehicleType.SM, 2, null);
        when(modelMapper.map(requestDTO, VehicleTypeRequirement.class)).thenReturn(vehicleTypeRequirement);
        when(repository.save(vehicleTypeRequirement)).thenReturn(vehicleTypeRequirement);
        when(modelMapper.map(vehicleTypeRequirement, VehicleTypeRequirementResponseDTO.class))
                .thenReturn(new VehicleTypeRequirementResponseDTO(1L, VehicleType.SM, 2, null));

        VehicleTypeRequirementResponseDTO responseDTO = service.createVehicleTypeRequirement(requestDTO);

        assertNotNull(responseDTO);
        assertEquals(1L, responseDTO.getId());
        assertEquals(VehicleType.SM, responseDTO.getVehicleType());
    }

    @Test
    void getVehicleTypeRequirementById_ShouldThrowException_WhenNotFound() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        VehicleTypeRequirementNotFoundException exception = assertThrows(VehicleTypeRequirementNotFoundException.class,
                () -> service.getVehicleTypeRequirementById(1L));

        assertEquals("VehicleTypeRequirement not found with id: 1", exception.getMessage());
    }
}
