package com.csse.api.controller;


import com.csse.api.dto.vehiclePrice.VehiclePriceRequest;
import com.csse.api.dto.vehiclePrice.VehiclePriceResponse;
import com.csse.api.model.VehiclePrice;
import com.csse.api.service.VehiclePriceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-prices")
public class VehiclePriceController {

    private final VehiclePriceService vehiclePriceService;
    private final ModelMapper modelMapper;

    @Autowired
    public VehiclePriceController(VehiclePriceService vehiclePriceService, ModelMapper modelMapper) {
        this.vehiclePriceService = vehiclePriceService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<VehiclePriceResponse> createVehiclePrice(@RequestBody VehiclePriceRequest request) {
        VehiclePrice vehiclePrice = modelMapper.map(request, VehiclePrice.class);
        VehiclePrice savedVehiclePrice = vehiclePriceService.createVehiclePrice(vehiclePrice);
        VehiclePriceResponse response = modelMapper.map(savedVehiclePrice, VehiclePriceResponse.class);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiclePriceResponse> getVehiclePriceById(@PathVariable long id) {
        VehiclePrice vehiclePrice = vehiclePriceService.getVehiclePriceById(id);
        VehiclePriceResponse response = modelMapper.map(vehiclePrice, VehiclePriceResponse.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<VehiclePriceResponse>> getAllVehiclePrices() {
        List<VehiclePrice> vehiclePrices = vehiclePriceService.getAllVehiclePrices();
        List<VehiclePriceResponse> response = vehiclePrices.stream()
                .map(vehiclePrice -> modelMapper.map(vehiclePrice, VehiclePriceResponse.class))
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiclePriceResponse> updateVehiclePrice(@PathVariable long id, @RequestBody VehiclePriceRequest request) {
        VehiclePrice vehiclePrice = modelMapper.map(request, VehiclePrice.class);
        VehiclePrice updatedVehiclePrice = vehiclePriceService.updateVehiclePrice(id, vehiclePrice);
        VehiclePriceResponse response = modelMapper.map(updatedVehiclePrice, VehiclePriceResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiclePrice(@PathVariable long id) {
        vehiclePriceService.deleteVehiclePrice(id);
        return ResponseEntity.noContent().build();
    }
}
