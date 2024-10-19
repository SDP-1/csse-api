package com.csse.api.service;

import com.csse.api.dto.SpecialWasteRequestRequestDTO;
import com.csse.api.dto.SpecialWasteRequestResponseDTO;
import com.csse.api.exception.SpecialWasteRequestNotFoundException;
import com.csse.api.model.SpecialWasteRequest;
import com.csse.api.repository.SpecialWasteRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SpecialWasteRequestServiceTest {

    @InjectMocks
    private SpecialWasteRequestService service;

    @Mock
    private SpecialWasteRequestRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private SpecialWasteRequest specialWasteRequest;
    private SpecialWasteRequestRequestDTO requestDTO;
    private SpecialWasteRequestResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        specialWasteRequest = new SpecialWasteRequest(1L, "Title", "Description", "Pending", null, "Location", null, null);
        requestDTO = new SpecialWasteRequestRequestDTO("Title", "Description", "Pending", 1L, "Location", null, null);
        responseDTO = new SpecialWasteRequestResponseDTO(1L, "Title", "Description", "Pending", 1L, "Location", null, null);
    }

    @Test
    void create_ShouldReturnResponseDTO() {
        when(modelMapper.map(requestDTO, SpecialWasteRequest.class)).thenReturn(specialWasteRequest);
        when(repository.save(specialWasteRequest)).thenReturn(specialWasteRequest);
        when(modelMapper.map(specialWasteRequest, SpecialWasteRequestResponseDTO.class)).thenReturn(responseDTO);

        SpecialWasteRequestResponseDTO result = service.create(requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO.getId(), result.getId());
        verify(repository, times(1)).save(any(SpecialWasteRequest.class));
    }

    @Test
    void getById_WhenFound_ShouldReturnResponseDTO() {
        when(repository.findById(1L)).thenReturn(Optional.of(specialWasteRequest));
        when(modelMapper.map(specialWasteRequest, SpecialWasteRequestResponseDTO.class)).thenReturn(responseDTO);

        SpecialWasteRequestResponseDTO result = service.getById(1L);

        assertNotNull(result);
        assertEquals(responseDTO.getId(), result.getId());
    }

    @Test
    void getById_WhenNotFound_ShouldThrowSpecialWasteRequestNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SpecialWasteRequestNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    void update_WhenFound_ShouldReturnResponseDTO() {
        when(repository.findById(1L)).thenReturn(Optional.of(specialWasteRequest));
        modelMapper.map(requestDTO, specialWasteRequest); // Just map without expecting a return
        when(repository.save(specialWasteRequest)).thenReturn(specialWasteRequest);
        when(modelMapper.map(specialWasteRequest, SpecialWasteRequestResponseDTO.class)).thenReturn(responseDTO);

        SpecialWasteRequestResponseDTO result = service.update(1L, requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO.getId(), result.getId());
        verify(repository, times(1)).save(specialWasteRequest);
    }

    @Test
    void update_WhenNotFound_ShouldThrowSpecialWasteRequestNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SpecialWasteRequestNotFoundException.class, () -> service.update(1L, requestDTO));
    }

    @Test
    void delete_ShouldCallDelete() {
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
