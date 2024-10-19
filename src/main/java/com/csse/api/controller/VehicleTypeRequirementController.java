package com.csse.api.controller;

import com.csse.api.dto.vehicleType.VehicleTypeRequirementRequestDTO;
import com.csse.api.dto.vehicleType.VehicleTypeRequirementResponseDTO;
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
    public ResponseEntity<VehicleTypeRequirementResponseDTO> createVehicleTypeRequirement(@RequestBody VehicleTypeRequirementRequestDTO request) {
        VehicleTypeRequirementResponseDTO response = vehicleTypeRequirementService.createVehicleTypeRequirement(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleTypeRequirementResponseDTO> getVehicleTypeRequirementById(@PathVariable long id) {
        Optional<VehicleTypeRequirementResponseDTO> response = Optional.ofNullable(vehicleTypeRequirementService.getVehicleTypeRequirementById(id));
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<VehicleTypeRequirementResponseDTO>> getAllVehicleTypeRequirements() {
        List<VehicleTypeRequirementResponseDTO> response = vehicleTypeRequirementService.getAllVehicleTypeRequirements();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleTypeRequirementResponseDTO> updateVehicleTypeRequirement(@PathVariable long id, @RequestBody VehicleTypeRequirementRequestDTO request) {
        VehicleTypeRequirementResponseDTO response = vehicleTypeRequirementService.updateVehicleTypeRequirement(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleTypeRequirement(@PathVariable long id) {
        vehicleTypeRequirementService.deleteVehicleTypeRequirement(id);
        return ResponseEntity.noContent().build();
    }
}
