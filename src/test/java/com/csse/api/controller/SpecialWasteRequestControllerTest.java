package com.csse.api.controller;

import com.csse.api.dto.SpecialWasteRequestRequestDTO;
import com.csse.api.dto.SpecialWasteRequestResponseDTO;
import com.csse.api.exception.SpecialWasteRequestNotFoundException;
import com.csse.api.service.SpecialWasteRequestService;
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

class SpecialWasteRequestControllerTest {

    @InjectMocks
    private SpecialWasteRequestController controller;

    @Mock
    private SpecialWasteRequestService service;

    private SpecialWasteRequestRequestDTO requestDTO;
    private SpecialWasteRequestResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        requestDTO = new SpecialWasteRequestRequestDTO("Title", "Description", "Pending", 1L, "Location", null, null);
        responseDTO = new SpecialWasteRequestResponseDTO(1L, "Title", "Description", "Pending", 1L, "Location", null, null);
    }

    @Test
    void create_ShouldReturnResponseEntity() {
        when(service.create(any(SpecialWasteRequestRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<SpecialWasteRequestResponseDTO> result = controller.create(requestDTO);

        assertEquals(responseDTO, result.getBody());
        assertEquals(200, result.getStatusCodeValue());
        verify(service, times(1)).create(any(SpecialWasteRequestRequestDTO.class));
    }

    @Test
    void getAll_ShouldReturnResponseEntity() {
        List<SpecialWasteRequestResponseDTO> responseList = Arrays.asList(responseDTO);
        when(service.getAll()).thenReturn(responseList);

        ResponseEntity<List<SpecialWasteRequestResponseDTO>> result = controller.getAll();

        assertEquals(responseList, result.getBody());
        assertEquals(200, result.getStatusCodeValue());
        verify(service, times(1)).getAll();
    }

    @Test
    void getById_WhenFound_ShouldReturnResponseEntity() {
        when(service.getById(1L)).thenReturn(responseDTO);

        ResponseEntity<SpecialWasteRequestResponseDTO> result = controller.getById(1L);

        assertEquals(responseDTO, result.getBody());
        assertEquals(200, result.getStatusCodeValue());
        verify(service, times(1)).getById(1L);
    }

    @Test
    void getById_WhenNotFound_ShouldThrowSpecialWasteRequestNotFoundException() {
        when(service.getById(1L)).thenThrow(new SpecialWasteRequestNotFoundException(1L));

        assertThrows(SpecialWasteRequestNotFoundException.class, () -> controller.getById(1L));
        verify(service, times(1)).getById(1L);
    }

    @Test
    void update_ShouldReturnResponseEntity() {
        when(service.update(1L, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<SpecialWasteRequestResponseDTO> result = controller.update(1L, requestDTO);

        assertEquals(responseDTO, result.getBody());
        assertEquals(200, result.getStatusCodeValue());
        verify(service, times(1)).update(1L, requestDTO);
    }

    @Test
    void delete_ShouldReturnNoContent() {
        ResponseEntity<Void> result = controller.delete(1L);

        assertEquals(204, result.getStatusCodeValue());
        verify(service, times(1)).delete(1L);
    }
}
