package com.csse.api.service;

import com.csse.api.dto.tracking_device.TrackingDeviceRequestDTO;
import com.csse.api.dto.tracking_device.TrackingDeviceResponseDTO;
import com.csse.api.enums.TrackingDeviceStatus;
import com.csse.api.exception.ResourceNotFoundException;
import com.csse.api.exception.TrackingDeviceException;
import com.csse.api.model.TrackingDevice;
import com.csse.api.repository.TrackingDeviceRepository;
import com.csse.api.enums.BinStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TrackingDeviceServiceTest {

    @Mock
    private TrackingDeviceRepository trackingDeviceRepository;

    @InjectMocks
    private TrackingDeviceService trackingDeviceService;

    private TrackingDevice existingDevice;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        existingDevice = new TrackingDevice();
        existingDevice.setId(1L);
        existingDevice.setName("OldDevice");
        existingDevice.setCurrentWasteLevel(20.0f);
        existingDevice.setType("TypeA");
        existingDevice.setStatus(TrackingDeviceStatus.ACTIVE);
        existingDevice.setBinStatus(BinStatus.HALF_FULL);
    }

    @Test
    void testUpdateTrackingDevice_ShouldReturnUpdatedDevice_WhenDeviceExists() {
        TrackingDeviceRequestDTO requestDTO = new TrackingDeviceRequestDTO();
        requestDTO.setName("UpdatedDevice");
        requestDTO.setCurrentWasteLevel(50.0f);
        requestDTO.setType("TypeA");
        requestDTO.setStatus(TrackingDeviceStatus.ACTIVE);
        requestDTO.setBinStatus(BinStatus.OVERFLOWING);

        when(trackingDeviceRepository.findById(1L)).thenReturn(Optional.of(existingDevice));
        when(trackingDeviceRepository.save(any(TrackingDevice.class))).thenReturn(existingDevice);

        TrackingDeviceResponseDTO responseDTO = trackingDeviceService.updateTrackingDevice(1L, requestDTO);

        assertEquals("UpdatedDevice", responseDTO.getName());
        assertEquals(50.0f, responseDTO.getCurrentWasteLevel());
        assertEquals(BinStatus.OVERFLOWING, responseDTO.getBinStatus());
        verify(trackingDeviceRepository, times(1)).save(any(TrackingDevice.class));
    }

    @Test
    void testUpdateTrackingDevice_ShouldThrowResourceNotFound_WhenDeviceDoesNotExist() {
        TrackingDeviceRequestDTO requestDTO = new TrackingDeviceRequestDTO();
        requestDTO.setName("UpdatedDevice");
        requestDTO.setCurrentWasteLevel(50.0f);
        requestDTO.setBinStatus(BinStatus.HALF_FULL);

        when(trackingDeviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            trackingDeviceService.updateTrackingDevice(1L, requestDTO);
        });
    }

    @Test
    void testUpdateTrackingDevice_ShouldThrowTrackingDeviceException_WhenWasteLevelIsNegative() {
        TrackingDeviceRequestDTO requestDTO = new TrackingDeviceRequestDTO();
        requestDTO.setName("UpdatedDevice");
        requestDTO.setCurrentWasteLevel(-10.0f);

        when(trackingDeviceRepository.findById(1L)).thenReturn(Optional.of(existingDevice));

        assertThrows(TrackingDeviceException.class, () -> {
            trackingDeviceService.updateTrackingDevice(1L, requestDTO);
        });
    }
}
