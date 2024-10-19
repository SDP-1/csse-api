package com.csse.api.service;

import com.csse.api.dto.wasteType.WasteTypeRequestDTO;
import com.csse.api.dto.wasteType.WasteTypeResponseDTO;
import com.csse.api.exception.WasteTypeNotFoundException;
import com.csse.api.model.WasteType;
import com.csse.api.repository.WasteTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WasteTypeControllerTestTest {

    @InjectMocks
    private WasteTypeService wasteTypeService;

    @Mock
    private WasteTypeRepository wasteTypeRepository;

    @Mock
    private ModelMapper modelMapper;

    private WasteType wasteType;
    private WasteTypeResponseDTO wasteTypeResponseDTO;
    private WasteTypeRequestDTO wasteTypeRequestDTO;

    @BeforeEach
    void setUp() {
        wasteType = new WasteType(1L, "Plastic", "Used plastic waste", 10.0f, 5.0f, 15.0f, null, null);
        wasteTypeResponseDTO = new WasteTypeResponseDTO(1L, "Plastic", "Used plastic waste", 10.0f, 5.0f, 15.0f);
        wasteTypeRequestDTO = new WasteTypeRequestDTO("Plastic", "Used plastic waste", 10.0f, 5.0f, 15.0f);
    }

    @Test
    void getAllWasteTypes_ShouldReturnListOfWasteTypeResponseDTO() {
        when(wasteTypeRepository.findAll()).thenReturn(List.of(wasteType));
        when(modelMapper.map(wasteType, WasteTypeResponseDTO.class)).thenReturn(wasteTypeResponseDTO);

        var response = wasteTypeService.getAllWasteTypes();

        assertEquals(1, response.getBody().size());
        assertEquals(wasteTypeResponseDTO, response.getBody().get(0));
        verify(wasteTypeRepository, times(1)).findAll();
    }

    @Test
    void getWasteTypeById_ShouldReturnWasteTypeResponseDTO_WhenFound() {
        when(wasteTypeRepository.findById(1L)).thenReturn(Optional.of(wasteType));
        when(modelMapper.map(wasteType, WasteTypeResponseDTO.class)).thenReturn(wasteTypeResponseDTO);

        var response = wasteTypeService.getWasteTypeById(1L);

        assertEquals(wasteTypeResponseDTO, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getWasteTypeById_ShouldThrowException_WhenNotFound() {
        when(wasteTypeRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(WasteTypeNotFoundException.class, () -> {
            wasteTypeService.getWasteTypeById(1L);
        });

        assertEquals("WasteType not found with id: 1", exception.getMessage());
    }

    @Test
    void createWasteType_ShouldReturnCreatedWasteTypeResponseDTO() {
        when(modelMapper.map(wasteTypeRequestDTO, WasteType.class)).thenReturn(wasteType);
        when(wasteTypeRepository.save(wasteType)).thenReturn(wasteType);
        when(modelMapper.map(wasteType, WasteTypeResponseDTO.class)).thenReturn(wasteTypeResponseDTO);

        var response = wasteTypeService.createWasteType(wasteTypeRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(wasteTypeResponseDTO, response.getBody());
    }

    @Test
    void updateWasteType_ShouldReturnUpdatedWasteTypeResponseDTO_WhenFound() {
        when(wasteTypeRepository.existsById(1L)).thenReturn(true);
        when(modelMapper.map(wasteTypeRequestDTO, WasteType.class)).thenReturn(wasteType);
        when(wasteTypeRepository.save(wasteType)).thenReturn(wasteType);
        when(modelMapper.map(wasteType, WasteTypeResponseDTO.class)).thenReturn(wasteTypeResponseDTO);

        var response = wasteTypeService.updateWasteType(1L, wasteTypeRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wasteTypeResponseDTO, response.getBody());
    }

    @Test
    void updateWasteType_ShouldThrowException_WhenNotFound() {
        when(wasteTypeRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(WasteTypeNotFoundException.class, () -> {
            wasteTypeService.updateWasteType(1L, wasteTypeRequestDTO);
        });

        assertEquals("WasteType not found with id: 1", exception.getMessage());
    }

    @Test
    void deleteWasteType_ShouldReturnNoContent_WhenDeleted() {
        when(wasteTypeRepository.existsById(1L)).thenReturn(true);

        var response = wasteTypeService.deleteWasteType(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(wasteTypeRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteWasteType_ShouldThrowException_WhenNotFound() {
        when(wasteTypeRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(WasteTypeNotFoundException.class, () -> {
            wasteTypeService.deleteWasteType(1L);
        });

        assertEquals("WasteType not found with id: 1", exception.getMessage());
    }
}
