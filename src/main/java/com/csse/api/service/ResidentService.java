package com.csse.api.service;

import com.csse.api.dto.resident.ResidentRequestDTO;
import com.csse.api.dto.resident.ResidentResponseDTO;
import com.csse.api.exception.ResidentNotFoundException;
import com.csse.api.model.Bin;
import com.csse.api.model.Resident;
import com.csse.api.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository repository;

    public ResidentResponseDTO create(ResidentRequestDTO dto) {
        Resident resident = new Resident();
        resident.setName(dto.getName());
        resident.setAddress(dto.getAddress());
        resident.setResidentialType(dto.getResidentialType());

        // Set the WMA entity based on the wmaId
        // Assuming WMA repository is available to fetch WMA by ID
        // WMA wma = wmaRepository.findById(dto.getWmaId()).orElse(null);
        // resident.setWma(wma);

        // Handle bins, transactions, notifications similarly if needed

        return mapToResponseDTO(repository.save(resident));
    }

    public List<ResidentResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public ResidentResponseDTO getById(long id) {
        Resident resident = repository.findById(id)
                .orElseThrow(() -> new ResidentNotFoundException("Resident not found with ID: " + id));
        return mapToResponseDTO(resident);
    }

    public ResidentResponseDTO update(long id, ResidentRequestDTO dto) {
        Resident resident = repository.findById(id)
                .orElseThrow(() -> new ResidentNotFoundException("Resident not found with ID: " + id));

        resident.setName(dto.getName());
        resident.setAddress(dto.getAddress());
        resident.setResidentialType(dto.getResidentialType());

        // Update WMA and other relationships as needed

        return mapToResponseDTO(repository.save(resident));
    }

    public void delete(long id) {
        if (!repository.existsById(id)) {
            throw new ResidentNotFoundException("Resident not found with ID: " + id);
        }
        repository.deleteById(id);
    }

    private ResidentResponseDTO mapToResponseDTO(Resident resident) {
        ResidentResponseDTO dto = new ResidentResponseDTO();
        dto.setId(resident.getId());
        dto.setName(resident.getName());
        dto.setAddress(resident.getAddress());
        dto.setResidentialType(resident.getResidentialType());

        return dto;
    }
}
