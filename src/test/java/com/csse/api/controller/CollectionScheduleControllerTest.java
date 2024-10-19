package com.csse.api.controller;

import com.csse.api.dto.collection_schedule.CollectionScheduleRequestDTO;
import com.csse.api.dto.collection_schedule.CollectionScheduleResponseDTO;
import com.csse.api.exception.CollectionScheduleNotFoundException;
import com.csse.api.service.CollectionScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CollectionScheduleControllerTest {

    @InjectMocks
    private CollectionScheduleController collectionScheduleController;

    @Mock
    private CollectionScheduleService collectionScheduleService;

    private CollectionScheduleResponseDTO responseDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        responseDTO = new CollectionScheduleResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setStartDate(new Date());
        responseDTO.setEndDate(new Date());
        responseDTO.setFrequency(null);
        responseDTO.setRouteId(1L);
    }

    @Test
    void create_ShouldReturnCreatedResponseEntity() {
        CollectionScheduleRequestDTO requestDTO = new CollectionScheduleRequestDTO();
        requestDTO.setStartDate(new Date());
        requestDTO.setEndDate(new Date());
        requestDTO.setFrequency(null);
        requestDTO.setRouteId(1L);

        when(collectionScheduleService.create(any(CollectionScheduleRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<CollectionScheduleResponseDTO> response = collectionScheduleController.create(requestDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(responseDTO);
    }

    @Test
    void getAll_ShouldReturnListResponseEntity() {
        when(collectionScheduleService.getAll()).thenReturn(List.of(responseDTO));

        ResponseEntity<List<CollectionScheduleResponseDTO>> response = collectionScheduleController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().get(0)).isEqualTo(responseDTO);
    }

    @Test
    void getById_ShouldReturnResponseEntity_WhenExists() {
        when(collectionScheduleService.getById(1L)).thenReturn(responseDTO);

        ResponseEntity<CollectionScheduleResponseDTO> response = collectionScheduleController.getById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseDTO);
    }

    @Test
    void getById_ShouldReturnNotFound_WhenNotExists() {
        // Arrange
        long nonExistentId = 999L;
        when(collectionScheduleService.getById(nonExistentId)).thenThrow(new CollectionScheduleNotFoundException("Collection schedule with id " + nonExistentId + " not found"));

        // Act
        ResponseEntity<CollectionScheduleResponseDTO> response;
        try {
            response = collectionScheduleController.getById(nonExistentId);
        } catch (CollectionScheduleNotFoundException ex) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    void update_ShouldReturnUpdatedResponseEntity_WhenExists() {
        CollectionScheduleRequestDTO requestDTO = new CollectionScheduleRequestDTO();
        requestDTO.setStartDate(new Date(System.currentTimeMillis() + 100000));
        requestDTO.setEndDate(new Date(System.currentTimeMillis() + 200000));
        requestDTO.setFrequency(null);
        requestDTO.setRouteId(1L);

        when(collectionScheduleService.update(eq(1L), any(CollectionScheduleRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<CollectionScheduleResponseDTO> response = collectionScheduleController.update(1L, requestDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseDTO);
    }

    @Test
    void update_ShouldReturnNotFound_WhenNotExists() {
        CollectionScheduleRequestDTO requestDTO = new CollectionScheduleRequestDTO();

        when(collectionScheduleService.update(eq(999L), any(CollectionScheduleRequestDTO.class)))
                .thenThrow(new CollectionScheduleNotFoundException("Collection schedule with id 999 not found"));

        ResponseEntity<CollectionScheduleResponseDTO> response = collectionScheduleController.update(999L, requestDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void delete_ShouldReturnNoContent() {
        doNothing().when(collectionScheduleService).delete(1L);

        ResponseEntity<Void> response = collectionScheduleController.delete(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
