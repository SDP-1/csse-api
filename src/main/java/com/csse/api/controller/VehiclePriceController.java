package com.csse.api.controller;

import com.csse.api.dto.vehiclePrice.VehiclePriceRequestDTO;
import com.csse.api.dto.vehiclePrice.VehiclePriceResponseDTO;
import com.csse.api.exception.VehiclePriceNotFoundException;
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
    public ResponseEntity<VehiclePriceResponseDTO> createVehiclePrice(@RequestBody VehiclePriceRequestDTO request) {
        VehiclePrice vehiclePrice = modelMapper.map(request, VehiclePrice.class);
        VehiclePrice savedVehiclePrice = vehiclePriceService.createVehiclePrice(vehiclePrice);
        VehiclePriceResponseDTO response = modelMapper.map(savedVehiclePrice, VehiclePriceResponseDTO.class);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiclePriceResponseDTO> getVehiclePriceById(@PathVariable long id) {
        VehiclePrice vehiclePrice = vehiclePriceService.getVehiclePriceById(id);
        VehiclePriceResponseDTO response = modelMapper.map(vehiclePrice, VehiclePriceResponseDTO.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<VehiclePriceResponseDTO>> getAllVehiclePrices() {
        List<VehiclePrice> vehiclePrices = vehiclePriceService.getAllVehiclePrices();
        List<VehiclePriceResponseDTO> response = vehiclePrices.stream()
                .map(vehiclePrice -> modelMapper.map(vehiclePrice, VehiclePriceResponseDTO.class))
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiclePriceResponseDTO> updateVehiclePrice(@PathVariable long id, @RequestBody VehiclePriceRequestDTO request) {
        VehiclePrice vehiclePrice = modelMapper.map(request, VehiclePrice.class);
        VehiclePrice updatedVehiclePrice = vehiclePriceService.updateVehiclePrice(id, vehiclePrice);
        VehiclePriceResponseDTO response = modelMapper.map(updatedVehiclePrice, VehiclePriceResponseDTO.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiclePrice(@PathVariable long id) {
        vehiclePriceService.deleteVehiclePrice(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(VehiclePriceNotFoundException.class)
    public ResponseEntity<String> handleVehiclePriceNotFound(VehiclePriceNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
