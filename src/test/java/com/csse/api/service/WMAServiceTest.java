package com.csse.api.service;

import com.csse.api.dto.wma.WMARequestDTO;
import com.csse.api.dto.wma.WMAResponseDTO;
import com.csse.api.model.WMA;
import com.csse.api.repository.WMARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WMAServiceTest {

    @Mock
    private WMARepository wmaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private WMAService wmaService;

    private WMARequestDTO wmaRequestDTO;
    private WMAResponseDTO wmaResponseDTO;
    private WMA wma;

    @BeforeEach
    public void setUp() {
        wmaRequestDTO = new WMARequestDTO("Authority Name", "Region", "1234567890", "Address", null);
        wma = new WMA(1L, "Authority Name", "Region", "1234567890", "Address", null, null, null, null, null, null);
        wmaResponseDTO = new WMAResponseDTO(1L, "Authority Name", "Region", "1234567890", "Address", null);
    }

    @Test
    public void createWMA_ShouldReturnCreatedWMA() {
        when(modelMapper.map(wmaRequestDTO, WMA.class)).thenReturn(wma);
        when(wmaRepository.save(wma)).thenReturn(wma);
        when(modelMapper.map(wma, WMAResponseDTO.class)).thenReturn(wmaResponseDTO);

        ResponseEntity<WMAResponseDTO> response = wmaService.createWMA(wmaRequestDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(wmaResponseDTO, response.getBody());
    }

    @Test
    public void getAllWMAs_ShouldReturnListOfWMAs() {
        when(wmaRepository.findAll()).thenReturn(List.of(wma));
        when(modelMapper.map(wma, WMAResponseDTO.class)).thenReturn(wmaResponseDTO);

        ResponseEntity<List<WMAResponseDTO>> response = wmaService.getAllWMAs();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(wmaResponseDTO, response.getBody().get(0));
    }

    @Test
    public void getWMAById_ExistingId_ShouldReturnWMA() {
        when(wmaRepository.findById(1L)).thenReturn(Optional.of(wma));
        when(modelMapper.map(wma, WMAResponseDTO.class)).thenReturn(wmaResponseDTO);

        ResponseEntity<WMAResponseDTO> response = wmaService.getWMAById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(wmaResponseDTO, response.getBody());
    }

    @Test
    public void getWMAById_NonExistingId_ShouldReturnNotFound() {
        when(wmaRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<WMAResponseDTO> response = wmaService.getWMAById(2L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void updateWMA_ExistingId_ShouldReturnUpdatedWMA() {
        // Arrange
        when(wmaRepository.findById(1L)).thenReturn(Optional.of(wma));
        when(modelMapper.map(wmaRequestDTO, WMA.class)).thenReturn(wma); // Update the existing WMA with the request DTO
        when(wmaRepository.save(wma)).thenReturn(wma);
        when(modelMapper.map(wma, WMAResponseDTO.class)).thenReturn(wmaResponseDTO);

        // Act
        ResponseEntity<WMAResponseDTO> response = wmaService.updateWMA(1L, wmaRequestDTO);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(wmaResponseDTO, response.getBody());
    }


    @Test
    public void updateWMA_NonExistingId_ShouldReturnNotFound() {
        when(wmaRepository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<WMAResponseDTO> response = wmaService.updateWMA(2L, wmaRequestDTO);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void deleteWMA_ExistingId_ShouldReturnNoContent() {
        when(wmaRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> response = wmaService.deleteWMA(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(wmaRepository).deleteById(1L);
    }

    @Test
    public void deleteWMA_NonExistingId_ShouldReturnNotFound() {
        when(wmaRepository.existsById(2L)).thenReturn(false);

        ResponseEntity<Void> response = wmaService.deleteWMA(2L);

        assertEquals(404, response.getStatusCodeValue());
        verify(wmaRepository, never()).deleteById(2L);
    }
}
