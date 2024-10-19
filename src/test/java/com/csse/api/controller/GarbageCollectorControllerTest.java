package com.csse.api.controller;

import com.csse.api.dto.GarbageCollectorRequestDTO;
import com.csse.api.dto.GarbageCollectorResponseDTO;
import com.csse.api.service.GarbageCollectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GarbageCollectorControllerTest {

    @Mock
    private GarbageCollectorService service;

    @InjectMocks
    private GarbageCollectorController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void create_ShouldReturnCreatedGarbageCollector() {
        GarbageCollectorRequestDTO requestDTO = new GarbageCollectorRequestDTO();
        requestDTO.setCollectorId("C123");

        GarbageCollectorResponseDTO responseDTO = new GarbageCollectorResponseDTO();
        responseDTO.setCollectorId("C123");

        when(service.create(any(GarbageCollectorRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<GarbageCollectorResponseDTO> response = controller.create(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("C123", response.getBody().getCollectorId());
        verify(service, times(1)).create(any(GarbageCollectorRequestDTO.class));
    }

    @Test
    public void getAll_ShouldReturnListOfGarbageCollectors() {
        List<GarbageCollectorResponseDTO> responseList = List.of(new GarbageCollectorResponseDTO());

        when(service.getAll()).thenReturn(responseList);

        ResponseEntity<List<GarbageCollectorResponseDTO>> response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseList.size(), response.getBody().size());
        verify(service, times(1)).getAll();
    }

    @Test
    public void getById_ShouldReturnGarbageCollector() {
        GarbageCollectorResponseDTO responseDTO = new GarbageCollectorResponseDTO();
        responseDTO.setCollectorId("C123");

        when(service.getById(1L)).thenReturn(responseDTO);

        ResponseEntity<GarbageCollectorResponseDTO> response = controller.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("C123", response.getBody().getCollectorId());
        verify(service, times(1)).getById(1L);
    }

    @Test
    public void update_ShouldReturnUpdatedGarbageCollector() {
        GarbageCollectorRequestDTO requestDTO = new GarbageCollectorRequestDTO();
        requestDTO.setCollectorId("C123-updated");

        GarbageCollectorResponseDTO responseDTO = new GarbageCollectorResponseDTO();
        responseDTO.setCollectorId("C123-updated");

        when(service.update(1L, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<GarbageCollectorResponseDTO> response = controller.update(1L, requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("C123-updated", response.getBody().getCollectorId());
        verify(service, times(1)).update(1L, requestDTO);
    }

    @Test
    public void delete_ShouldReturnNoContent() {
        ResponseEntity<Void> response = controller.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).delete(1L);
    }
}
