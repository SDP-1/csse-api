package com.csse.api.service;

import com.csse.api.dto.bin_types.BinTypesRequestDTO;
import com.csse.api.dto.bin_types.BinTypesResponseDTO;
import com.csse.api.exception.BinTypeNotFoundException;
import com.csse.api.model.BinTypes;
import com.csse.api.repository.BinTypesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BinTypesServiceTest {

    @InjectMocks
    private BinTypesService service;

    @Mock
    private BinTypesRepository repository;

    private BinTypes binType;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        binType = new BinTypes();
        binType.setId(1L);
        binType.setName("Recycling Bin");
        binType.setCapacity("50L");
        binType.setProducer("EcoCorp");
        binType.setType("Plastic");
    }

    @Test
    void create_ShouldReturnBinTypesResponseDTO() {
        BinTypesRequestDTO requestDTO = new BinTypesRequestDTO("Recycling Bin", "50L", "EcoCorp", "Plastic");

        when(repository.save(any(BinTypes.class))).thenReturn(binType);

        BinTypesResponseDTO responseDTO = service.create(requestDTO);

        assertNotNull(responseDTO);
        assertEquals(binType.getId(), responseDTO.getId());
        assertEquals(binType.getName(), responseDTO.getName());
    }

    @Test
    void getAll_ShouldReturnListOfBinTypesResponseDTO() {
        when(repository.findAll()).thenReturn(Arrays.asList(binType));

        var responseList = service.getAll();

        assertEquals(1, responseList.size());
        assertEquals(binType.getName(), responseList.get(0).getName());
    }

    @Test
    void getById_ShouldReturnBinTypesResponseDTO() {
        when(repository.findById(1L)).thenReturn(Optional.of(binType));

        BinTypesResponseDTO responseDTO = service.getById(1L);

        assertEquals(binType.getName(), responseDTO.getName());
    }

    @Test
    void getById_ShouldThrowException_WhenBinTypeNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BinTypeNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    void update_ShouldReturnUpdatedBinTypesResponseDTO() {
        BinTypesRequestDTO requestDTO = new BinTypesRequestDTO("Updated Bin", "70L", "NewCorp", "Metal");
        when(repository.findById(1L)).thenReturn(Optional.of(binType));
        when(repository.save(any(BinTypes.class))).thenReturn(binType);

        BinTypesResponseDTO responseDTO = service.update(1L, requestDTO);

        assertEquals("Updated Bin", responseDTO.getName());
        assertEquals("70L", responseDTO.getCapacity());
    }

    @Test
    void update_ShouldThrowException_WhenBinTypeNotFound() {
        BinTypesRequestDTO requestDTO = new BinTypesRequestDTO("Updated Bin", "70L", "NewCorp", "Metal");
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BinTypeNotFoundException.class, () -> service.update(1L, requestDTO));
    }

    @Test
    void delete_ShouldNotThrowException_WhenBinTypeExists() {
        when(repository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> service.delete(1L));
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void delete_ShouldThrowException_WhenBinTypeNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(BinTypeNotFoundException.class, () -> service.delete(1L));
    }
}
