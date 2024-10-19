package com.csse.api.controller;

import com.csse.api.dto.collector_assignment.CollectorAssignmentRequestDTO;
import com.csse.api.dto.collector_assignment.CollectorAssignmentResponseDTO;
import com.csse.api.service.CollectorAssignmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CollectorAssignmentControllerTest {

    @InjectMocks
    private CollectorAssignmentController controller;

    @Mock
    private CollectorAssignmentService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        CollectorAssignmentRequestDTO requestDTO = new CollectorAssignmentRequestDTO(1L, 2L, new Date());
        CollectorAssignmentResponseDTO responseDTO = new CollectorAssignmentResponseDTO(1L, 1L, 2L, new Date());

        when(service.create(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<CollectorAssignmentResponseDTO> response = controller.create(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void getAll() {
        when(service.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<CollectorAssignmentResponseDTO>> response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void getById() {
        CollectorAssignmentResponseDTO responseDTO = new CollectorAssignmentResponseDTO(1L, 1L, 2L, new Date());

        when(service.getById(1L)).thenReturn(responseDTO);

        ResponseEntity<CollectorAssignmentResponseDTO> response = controller.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void update() {
        CollectorAssignmentRequestDTO requestDTO = new CollectorAssignmentRequestDTO(1L, 2L, new Date());
        CollectorAssignmentResponseDTO responseDTO = new CollectorAssignmentResponseDTO(1L, 1L, 2L, new Date());

        when(service.update(1L, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<CollectorAssignmentResponseDTO> response = controller.update(1L, requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void delete() {
        ResponseEntity<Void> response = controller.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).delete(1L);
    }
}
