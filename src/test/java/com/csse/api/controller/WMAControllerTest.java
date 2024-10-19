package com.csse.api.controller;

import com.csse.api.dto.wma.WMARequestDTO;
import com.csse.api.dto.wma.WMAResponseDTO;
import com.csse.api.service.WMAService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WMAControllerTest {

    @Mock
    private WMAService wmaService;

    @InjectMocks
    private WMAController wmaController;

    private WMARequestDTO wmaRequestDTO;
    private WMAResponseDTO wmaResponseDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        wmaRequestDTO = new WMARequestDTO("Authority Name", "Region", "1234567890", "Address", null);
        wmaResponseDTO = new WMAResponseDTO(1L, "Authority Name", "Region", "1234567890", "Address", null);
    }

    @Test
    public void createWMA_ShouldReturnCreatedWMA() {
        when(wmaService.createWMA(any(WMARequestDTO.class))).thenReturn(ResponseEntity.status(201).body(wmaResponseDTO));

        ResponseEntity<WMAResponseDTO> response = wmaController.createWMA(wmaRequestDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(wmaResponseDTO, response.getBody());
    }

    @Test
    public void getAllWMAs_ShouldReturnListOfWMAs() {
        when(wmaService.getAllWMAs()).thenReturn(ResponseEntity.ok(List.of(wmaResponseDTO)));

        ResponseEntity<List<WMAResponseDTO>> response = wmaController.getAllWMAs();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(wmaResponseDTO, response.getBody().get(0));
    }

    @Test
    public void getWMAById_ExistingId_ShouldReturnWMA() {
        when(wmaService.getWMAById(1L)).thenReturn(ResponseEntity.ok(wmaResponseDTO));

        ResponseEntity<WMAResponseDTO> response = wmaController.getWMAById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(wmaResponseDTO, response.getBody());
    }

    @Test
    public void getWMAById_NonExistingId_ShouldReturnNotFound() {
        when(wmaService.getWMAById(2L)).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<WMAResponseDTO> response = wmaController.getWMAById(2L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void updateWMA_ExistingId_ShouldReturnUpdatedWMA() {
        when(wmaService.updateWMA(eq(1L), any(WMARequestDTO.class))).thenReturn(ResponseEntity.ok(wmaResponseDTO));

        ResponseEntity<WMAResponseDTO> response = wmaController.updateWMA(1L, wmaRequestDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(wmaResponseDTO, response.getBody());
    }

    @Test
    public void updateWMA_NonExistingId_ShouldReturnNotFound() {
        when(wmaService.updateWMA(eq(2L), any(WMARequestDTO.class))).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<WMAResponseDTO> response = wmaController.updateWMA(2L, wmaRequestDTO);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void deleteWMA_ExistingId_ShouldReturnNoContent() {
        when(wmaService.deleteWMA(1L)).thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<Void> response = wmaController.deleteWMA(1L);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void deleteWMA_NonExistingId_ShouldReturnNotFound() {
        when(wmaService.deleteWMA(2L)).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<Void> response = wmaController.deleteWMA(2L);

        assertEquals(404, response.getStatusCodeValue());
    }
}
