package com.csse.api.service;

import com.csse.api.dto.GarbageCollectorRequestDTO;
import com.csse.api.dto.GarbageCollectorResponseDTO;
import com.csse.api.enums.VehicleType;
import com.csse.api.exception.GarbageCollectorNotFoundException;
import com.csse.api.model.GarbageCollector;
import com.csse.api.repository.GarbageCollectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GarbageCollectorServiceTest {

    @Mock
    private GarbageCollectorRepository repository;

    @InjectMocks
    private GarbageCollectorService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void create_ShouldReturnGarbageCollectorResponseDTO() {
        GarbageCollectorRequestDTO requestDTO = new GarbageCollectorRequestDTO();
        requestDTO.setCollectorId("C123");
        requestDTO.setVehicleRegNo("ABC-123");
        requestDTO.setVehicleType(VehicleType.MD);
        requestDTO.setModel("Model X");
        requestDTO.setCurrentStatus("ACTIVE");
        requestDTO.setCurrentLocation("Location A");

        GarbageCollector savedCollector = new GarbageCollector();
        savedCollector.setId(1L);
        savedCollector.setCollectorId("C123");

        when(repository.save(any(GarbageCollector.class))).thenReturn(savedCollector);

        GarbageCollectorResponseDTO responseDTO = service.create(requestDTO);

        assertNotNull(responseDTO);
        assertEquals("C123", responseDTO.getCollectorId());
        verify(repository, times(1)).save(any(GarbageCollector.class));
    }

    @Test
    public void getById_ShouldReturnGarbageCollectorResponseDTO() {
        GarbageCollector collector = new GarbageCollector();
        collector.setId(1L);
        collector.setCollectorId("C123");

        when(repository.findById(1L)).thenReturn(Optional.of(collector));

        GarbageCollectorResponseDTO responseDTO = service.getById(1L);

        assertNotNull(responseDTO);
        assertEquals("C123", responseDTO.getCollectorId());
    }

    @Test
    public void getById_ShouldThrowGarbageCollectorNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GarbageCollectorNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    public void update_ShouldReturnGarbageCollectorResponseDTO() {
        GarbageCollector existingCollector = new GarbageCollector();
        existingCollector.setId(1L);
        existingCollector.setCollectorId("C123");

        when(repository.findById(1L)).thenReturn(Optional.of(existingCollector));
        when(repository.save(any(GarbageCollector.class))).thenReturn(existingCollector);

        GarbageCollectorRequestDTO requestDTO = new GarbageCollectorRequestDTO();
        requestDTO.setCollectorId("C123-updated");

        GarbageCollectorResponseDTO responseDTO = service.update(1L, requestDTO);

        assertEquals("C123-updated", responseDTO.getCollectorId());
    }

    @Test
    public void delete_ShouldCallRepositoryDeleteById() {
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
