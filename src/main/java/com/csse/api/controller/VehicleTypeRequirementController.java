package com.csse.api.controller;

import com.csse.api.dto.vehicleType.VehicleTypeRequirementRequest;
import com.csse.api.dto.vehicleType.VehicleTypeRequirementResponse;
import com.csse.api.service.VehicleTypeRequirementService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicle-type-requirements")
public class VehicleTypeRequirementController {

    private final VehicleTypeRequirementService vehicleTypeRequirementService;
    private final ModelMapper modelMapper;

    public VehicleTypeRequirementController(VehicleTypeRequirementService vehicleTypeRequirementService, ModelMapper modelMapper) {
        this.vehicleTypeRequirementService = vehicleTypeRequirementService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<VehicleTypeRequirementResponse> createVehicleTypeRequirement(@RequestBody VehicleTypeRequirementRequest request) {
        VehicleTypeRequirementResponse response = vehicleTypeRequirementService.createVehicleTypeRequirement(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleTypeRequirementResponse> getVehicleTypeRequirementById(@PathVariable long id) {
        Optional<VehicleTypeRequirementResponse> response = vehicleTypeRequirementService.getVehicleTypeRequirementById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<VehicleTypeRequirementResponse>> getAllVehicleTypeRequirements() {
        List<VehicleTypeRequirementResponse> response = vehicleTypeRequirementService.getAllVehicleTypeRequirements();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleTypeRequirementResponse> updateVehicleTypeRequirement(@PathVariable long id, @RequestBody VehicleTypeRequirementRequest request) {
        VehicleTypeRequirementResponse response = vehicleTypeRequirementService.updateVehicleTypeRequirement(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleTypeRequirement(@PathVariable long id) {
        vehicleTypeRequirementService.deleteVehicleTypeRequirement(id);
        return ResponseEntity.noContent().build();
    }
}
