package com.csse.api.service;

import com.csse.api.dto.GarbageCollectorRequestDTO;
import com.csse.api.dto.GarbageCollectorResponseDTO;
import com.csse.api.exception.GarbageCollectorNotFoundException;
import com.csse.api.model.GarbageCollector;
import com.csse.api.repository.GarbageCollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarbageCollectorService {

    @Autowired
    private GarbageCollectorRepository repository;

    public GarbageCollectorResponseDTO create(GarbageCollectorRequestDTO dto) {
        GarbageCollector entity = new GarbageCollector();
        entity.setCollectorId(dto.getCollectorId());
        entity.setVehicleRegNo(dto.getVehicleRegNo());
        entity.setVehicleType(dto.getVehicleType());
        entity.setModel(dto.getModel());
        entity.setCurrentStatus(dto.getCurrentStatus());
        entity.setCurrentLocation(dto.getCurrentLocation());
        // Set WMA and other relationships as needed

        return convertToResponseDTO(repository.save(entity));
    }

    public List<GarbageCollectorResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public GarbageCollectorResponseDTO getById(long id) {
        return repository.findById(id)
                .map(this::convertToResponseDTO)
                .orElseThrow(() -> new GarbageCollectorNotFoundException(id));
    }

    public GarbageCollectorResponseDTO update(long id, GarbageCollectorRequestDTO dto) {
        GarbageCollector entity = repository.findById(id)
                .orElseThrow(() -> new GarbageCollectorNotFoundException(id));

        entity.setCollectorId(dto.getCollectorId());
        entity.setVehicleRegNo(dto.getVehicleRegNo());
        entity.setVehicleType(dto.getVehicleType());
        entity.setModel(dto.getModel());
        entity.setCurrentStatus(dto.getCurrentStatus());
        entity.setCurrentLocation(dto.getCurrentLocation());
        // Update WMA and other relationships as needed

        return convertToResponseDTO(repository.save(entity));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    private GarbageCollectorResponseDTO convertToResponseDTO(GarbageCollector entity) {
        GarbageCollectorResponseDTO dto = new GarbageCollectorResponseDTO();
        dto.setId(entity.getId());
        dto.setCollectorId(entity.getCollectorId());
        dto.setVehicleRegNo(entity.getVehicleRegNo());
        dto.setVehicleType(entity.getVehicleType());
        dto.setModel(entity.getModel());
        dto.setCurrentStatus(entity.getCurrentStatus());
        dto.setCurrentLocation(entity.getCurrentLocation());
        return dto;
    }
}
