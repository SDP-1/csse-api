package com.csse.api.service;

import com.csse.api.dto.collector_assignment.CollectorAssignmentRequestDTO;
import com.csse.api.dto.collector_assignment.CollectorAssignmentResponseDTO;
import com.csse.api.exception.CollectorAssignmentNotFoundException;
import com.csse.api.model.CollectorAssignment;
import com.csse.api.model.CollectionSchedule;
import com.csse.api.model.GarbageCollector;
import com.csse.api.repository.CollectorAssignmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CollectorAssignmentServiceTest {

    @InjectMocks
    private CollectorAssignmentService service;

    @Mock
    private CollectorAssignmentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        CollectorAssignmentRequestDTO requestDTO = new CollectorAssignmentRequestDTO(1L, 2L, new Date());
        CollectorAssignment savedEntity = new CollectorAssignment(1L, new CollectionSchedule(1L), new GarbageCollector(2L), new Date());

        when(repository.save(any(CollectorAssignment.class))).thenReturn(savedEntity);

        CollectorAssignmentResponseDTO responseDTO = service.create(requestDTO);

        assertEquals(1L, responseDTO.getId());
        assertEquals(1L, responseDTO.getCollectionScheduleId());
        assertEquals(2L, responseDTO.getCollectorId());
    }

    @Test
    void getById_WhenExists() {
        CollectorAssignment entity = new CollectorAssignment(1L, new CollectionSchedule(1L), new GarbageCollector(2L), new Date());

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        CollectorAssignmentResponseDTO responseDTO = service.getById(1L);

        assertEquals(1L, responseDTO.getId());
    }

    @Test
    void getById_WhenDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CollectorAssignmentNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    void update() {
        CollectorAssignmentRequestDTO requestDTO = new CollectorAssignmentRequestDTO(1L, 2L, new Date());
        CollectorAssignment existingEntity = new CollectorAssignment(1L, new CollectionSchedule(1L), new GarbageCollector(2L), new Date());

        when(repository.findById(1L)).thenReturn(Optional.of(existingEntity));
        when(repository.save(any(CollectorAssignment.class))).thenReturn(existingEntity);

        CollectorAssignmentResponseDTO responseDTO = service.update(1L, requestDTO);

        assertEquals(1L, responseDTO.getId());
    }

    @Test
    void delete() {
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
