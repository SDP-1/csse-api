package com.csse.api.service;

import com.csse.api.exception.VehiclePriceNotFoundException;
import com.csse.api.model.VehiclePrice;
import com.csse.api.repository.VehiclePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiclePriceService {

    private final VehiclePriceRepository vehiclePriceRepository;

    @Autowired
    public VehiclePriceService(VehiclePriceRepository vehiclePriceRepository) {
        this.vehiclePriceRepository = vehiclePriceRepository;
    }

    public VehiclePrice createVehiclePrice(VehiclePrice vehiclePrice) {
        return vehiclePriceRepository.save(vehiclePrice);
    }

    public VehiclePrice getVehiclePriceById(long id) {
        return vehiclePriceRepository.findById(id)
                .orElseThrow(() -> new VehiclePriceNotFoundException("Vehicle Price not found with id: " + id));
    }

    public List<VehiclePrice> getAllVehiclePrices() {
        return vehiclePriceRepository.findAll();
    }

    public VehiclePrice updateVehiclePrice(long id, VehiclePrice vehiclePriceDetails) {
        VehiclePrice vehiclePrice = getVehiclePriceById(id);
        vehiclePrice.setVehicleType(vehiclePriceDetails.getVehicleType());
        vehiclePrice.setPrice(vehiclePriceDetails.getPrice());
        vehiclePrice.setGarbageCollector(vehiclePriceDetails.getGarbageCollector());
        return vehiclePriceRepository.save(vehiclePrice);
    }

    public void deleteVehiclePrice(long id) {
        getVehiclePriceById(id); // Check if it exists
        vehiclePriceRepository.deleteById(id);
    }
}
