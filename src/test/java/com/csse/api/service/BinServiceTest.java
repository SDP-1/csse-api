package com.csse.api.service;

import com.csse.api.dto.bin.BinRequestDTO;
import com.csse.api.dto.bin.BinResponseDTO;
import com.csse.api.exception.BinNotFoundException;
import com.csse.api.model.Bin;
import com.csse.api.model.BinTypes;
import com.csse.api.model.Resident;
import com.csse.api.model.TrackingDevice;
import com.csse.api.model.WasteType;
import com.csse.api.repository.BinRepository;
import com.csse.api.repository.BinTypesRepository;
import com.csse.api.repository.ResidentRepository;
import com.csse.api.repository.TrackingDeviceRepository;
import com.csse.api.repository.WasteTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BinServiceTest {

    @InjectMocks
    private BinService binService;

    @Mock
    private BinRepository binRepository;

    @Mock
    private WasteTypeRepository wasteTypeRepository;

    @Mock
    private ResidentRepository residentRepository;

    @Mock
    private BinTypesRepository binTypesRepository;

    @Mock
    private TrackingDeviceRepository trackingDeviceRepository;

    private BinRequestDTO binRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        binRequestDTO = new BinRequestDTO(1L, 1L, 1L, null);
    }

    @Test
    void create_ShouldReturnBinResponseDTO() {
        WasteType wasteType = new WasteType();
        wasteType.setId(1L);

        Resident resident = new Resident();
        resident.setId(1L);

        BinTypes binType = new BinTypes();
        binType.setId(1L);

        Bin bin = new Bin();
        bin.setId(1L);
        bin.setWasteType(wasteType);
        bin.setResident(resident);
        bin.setBinType(binType);

        when(wasteTypeRepository.findById(1L)).thenReturn(Optional.of(wasteType));
        when(residentRepository.findById(1L)).thenReturn(Optional.of(resident));
        when(binTypesRepository.findById(1L)).thenReturn(Optional.of(binType));
        when(binRepository.save(any(Bin.class))).thenReturn(bin);

        BinResponseDTO response = binService.create(binRequestDTO);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void create_ShouldThrowBinNotFoundException_WhenWasteTypeNotFound() {
        when(wasteTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BinNotFoundException.class, () -> binService.create(binRequestDTO));
    }

    @Test
    void getAll_ShouldReturnListOfBinResponseDTO() {
        WasteType wasteType = new WasteType();
        wasteType.setId(1L);

        Resident resident = new Resident();
        resident.setId(1L);

        BinTypes binType = new BinTypes();
        binType.setId(1L);

        Bin bin = new Bin();
        bin.setId(1L);
        bin.setWasteType(wasteType);
        bin.setResident(resident);
        bin.setBinType(binType);

        when(binRepository.findAll()).thenReturn(Collections.singletonList(bin));

        List<BinResponseDTO> response = binService.getAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(1L, response.get(0).getId());
    }



    @Test
    void getById_ShouldThrowBinNotFoundException_WhenBinNotFound() {
        // This is the only necessary setup for this test.
        when(binRepository.findById(1L)).thenReturn(Optional.empty());

        // Assert that the exception is thrown.
        assertThrows(BinNotFoundException.class, () -> binService.getById(1L));
    }


    @Test
    void update_ShouldReturnUpdatedBinResponseDTO() {
        WasteType wasteType = new WasteType();
        wasteType.setId(1L);

        Resident resident = new Resident();
        resident.setId(1L);

        BinTypes binType = new BinTypes();
        binType.setId(1L);

        Bin bin = new Bin();
        bin.setId(1L);
        bin.setWasteType(wasteType);
        bin.setResident(resident);
        bin.setBinType(binType);

        when(binRepository.findById(1L)).thenReturn(Optional.of(bin));
        when(wasteTypeRepository.findById(1L)).thenReturn(Optional.of(new WasteType()));
        when(residentRepository.findById(1L)).thenReturn(Optional.of(new Resident()));
        when(binTypesRepository.findById(1L)).thenReturn(Optional.of(new BinTypes()));
        when(binRepository.save(any(Bin.class))).thenReturn(bin);

        BinResponseDTO response = binService.update(1L, binRequestDTO);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void update_ShouldThrowBinNotFoundException_WhenBinNotFound() {
        when(binRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BinNotFoundException.class, () -> binService.update(1L, binRequestDTO));
    }

    @Test
    void delete_ShouldCallDeleteOnRepository() {
        binService.delete(1L);

        verify(binRepository, times(1)).deleteById(1L);
    }

    @Test
    void update_ShouldThrowBinNotFoundException_WhenWasteTypeNotFound() {
        when(binRepository.findById(1L)).thenReturn(Optional.of(new Bin()));
        when(wasteTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BinNotFoundException.class, () -> binService.update(1L, binRequestDTO));
    }

    @Test
    void update_ShouldThrowBinNotFoundException_WhenResidentNotFound() {
        when(binRepository.findById(1L)).thenReturn(Optional.of(new Bin()));
        when(wasteTypeRepository.findById(1L)).thenReturn(Optional.of(new WasteType()));
        when(residentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BinNotFoundException.class, () -> binService.update(1L, binRequestDTO));
    }

    @Test
    void update_ShouldThrowBinNotFoundException_WhenBinTypeNotFound() {
        when(binRepository.findById(1L)).thenReturn(Optional.of(new Bin()));
        when(wasteTypeRepository.findById(1L)).thenReturn(Optional.of(new WasteType()));
        when(residentRepository.findById(1L)).thenReturn(Optional.of(new Resident()));
        when(binTypesRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BinNotFoundException.class, () -> binService.update(1L, binRequestDTO));
    }
}
