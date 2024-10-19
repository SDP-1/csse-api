package com.csse.api.service;

import com.csse.api.dto.vehicleType.VehicleTypeRequirementRequestDTO;
import com.csse.api.dto.vehicleType.VehicleTypeRequirementResponseDTO;
import com.csse.api.exception.VehicleTypeRequirementNotFoundException;
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

    public VehicleTypeRequirementResponseDTO createVehicleTypeRequirement(VehicleTypeRequirementRequestDTO request) {
        VehicleTypeRequirement vehicleTypeRequirement = modelMapper.map(request, VehicleTypeRequirement.class);
        VehicleTypeRequirement savedRequirement = vehicleTypeRequirementRepository.save(vehicleTypeRequirement);
        return modelMapper.map(savedRequirement, VehicleTypeRequirementResponseDTO.class);
    }

    public VehicleTypeRequirementResponseDTO getVehicleTypeRequirementById(long id) {
        VehicleTypeRequirement requirement = vehicleTypeRequirementRepository.findById(id)
                .orElseThrow(() -> new VehicleTypeRequirementNotFoundException("VehicleTypeRequirement not found with id: " + id));
        return modelMapper.map(requirement, VehicleTypeRequirementResponseDTO.class);
    }

    public List<VehicleTypeRequirementResponseDTO> getAllVehicleTypeRequirements() {
        return vehicleTypeRequirementRepository.findAll().stream()
                .map(requirement -> modelMapper.map(requirement, VehicleTypeRequirementResponseDTO.class))
                .collect(Collectors.toList());
    }

    public VehicleTypeRequirementResponseDTO updateVehicleTypeRequirement(long id, VehicleTypeRequirementRequestDTO request) {
        VehicleTypeRequirement existingRequirement = vehicleTypeRequirementRepository.findById(id)
                .orElseThrow(() -> new VehicleTypeRequirementNotFoundException("VehicleTypeRequirement not found with id: " + id));
        modelMapper.map(request, existingRequirement);
        VehicleTypeRequirement updatedRequirement = vehicleTypeRequirementRepository.save(existingRequirement);
        return modelMapper.map(updatedRequirement, VehicleTypeRequirementResponseDTO.class);
    }

    public void deleteVehicleTypeRequirement(long id) {
        vehicleTypeRequirementRepository.deleteById(id);
    }
}
