package com.csse.api.service;

import com.csse.api.exception.VehiclePriceNotFoundException;
import com.csse.api.model.VehiclePrice;
import com.csse.api.repository.VehiclePriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehiclePriceServiceTest {

    @InjectMocks
    private VehiclePriceService vehiclePriceService;

    @Mock
    private VehiclePriceRepository vehiclePriceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getVehiclePriceById_VehiclePriceNotFound() {
        long vehiclePriceId = 1L;
        when(vehiclePriceRepository.findById(vehiclePriceId)).thenReturn(Optional.empty());

        VehiclePriceNotFoundException exception = assertThrows(VehiclePriceNotFoundException.class,
                () -> vehiclePriceService.getVehiclePriceById(vehiclePriceId));

        assertEquals("Vehicle Price not found with id: " + vehiclePriceId, exception.getMessage());
    }

    @Test
    void createVehiclePrice() {
        VehiclePrice vehiclePrice = new VehiclePrice();
        when(vehiclePriceRepository.save(vehiclePrice)).thenReturn(vehiclePrice);

        VehiclePrice created = vehiclePriceService.createVehiclePrice(vehiclePrice);

        assertNotNull(created);
        verify(vehiclePriceRepository).save(vehiclePrice);
    }

    @Test
    void updateVehiclePrice() {
        long vehiclePriceId = 1L;
        VehiclePrice existingVehiclePrice = new VehiclePrice();
        VehiclePrice vehiclePriceDetails = new VehiclePrice();

        when(vehiclePriceRepository.findById(vehiclePriceId)).thenReturn(Optional.of(existingVehiclePrice));
        when(vehiclePriceRepository.save(existingVehiclePrice)).thenReturn(existingVehiclePrice);

        VehiclePrice updatedVehiclePrice = vehiclePriceService.updateVehiclePrice(vehiclePriceId, vehiclePriceDetails);

        assertNotNull(updatedVehiclePrice);
        verify(vehiclePriceRepository).findById(vehiclePriceId);
        verify(vehiclePriceRepository).save(existingVehiclePrice);
    }

    @Test
    void deleteVehiclePrice() {
        long vehiclePriceId = 1L;
        VehiclePrice vehiclePrice = new VehiclePrice();
        when(vehiclePriceRepository.findById(vehiclePriceId)).thenReturn(Optional.of(vehiclePrice));

        vehiclePriceService.deleteVehiclePrice(vehiclePriceId);

        verify(vehiclePriceRepository).deleteById(vehiclePriceId);
    }

}
