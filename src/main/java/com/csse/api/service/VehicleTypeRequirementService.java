package com.csse.api.service;


import com.csse.api.dto.vehicleType.VehicleTypeRequirementRequest;
import com.csse.api.dto.vehicleType.VehicleTypeRequirementResponse;
import com.csse.api.model.VehicleTypeRequirement;
import com.csse.api.repository.VehicleTypeRequirementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleTypeRequirementService {

    private final VehicleTypeRequirementRepository vehicleTypeRequirementRepository;
    private final ModelMapper modelMapper;

    public VehicleTypeRequirementService(VehicleTypeRequirementRepository vehicleTypeRequirementRepository, ModelMapper modelMapper) {
        this.vehicleTypeRequirementRepository = vehicleTypeRequirementRepository;
        this.modelMapper = modelMapper;
    }

    public VehicleTypeRequirementResponse createVehicleTypeRequirement(VehicleTypeRequirementRequest request) {
        VehicleTypeRequirement vehicleTypeRequirement = modelMapper.map(request, VehicleTypeRequirement.class);
        VehicleTypeRequirement savedRequirement = vehicleTypeRequirementRepository.save(vehicleTypeRequirement);
        return modelMapper.map(savedRequirement, VehicleTypeRequirementResponse.class);
    }

    public Optional<VehicleTypeRequirementResponse> getVehicleTypeRequirementById(long id) {
        return vehicleTypeRequirementRepository.findById(id)
                .map(requirement -> modelMapper.map(requirement, VehicleTypeRequirementResponse.class));
    }

    public List<VehicleTypeRequirementResponse> getAllVehicleTypeRequirements() {
        return vehicleTypeRequirementRepository.findAll().stream()
                .map(requirement -> modelMapper.map(requirement, VehicleTypeRequirementResponse.class))
                .collect(Collectors.toList());
    }

    public VehicleTypeRequirementResponse updateVehicleTypeRequirement(long id, VehicleTypeRequirementRequest request) {
        VehicleTypeRequirement existingRequirement = vehicleTypeRequirementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        modelMapper.map(request, existingRequirement);
        VehicleTypeRequirement updatedRequirement = vehicleTypeRequirementRepository.save(existingRequirement);
        return modelMapper.map(updatedRequirement, VehicleTypeRequirementResponse.class);
    }

    public void deleteVehicleTypeRequirement(long id) {
        vehicleTypeRequirementRepository.deleteById(id);
    }
}
