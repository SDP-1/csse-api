package com.csse.api.service;

import com.csse.api.dto.clocation.CLocationRequestDTO;
import com.csse.api.dto.clocation.CLocationResponseDTO;
import com.csse.api.model.CLocation;
import com.csse.api.repository.CLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CLocationService {
    @Autowired
    private CLocationRepository cLocationRepository;

    public List<CLocationResponseDTO> getAllLocations() {
        return cLocationRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public CLocationResponseDTO getLocationById(long id) {
        return cLocationRepository.findById(id)
                .map(this::convertToResponseDTO)
                .orElse(null);
    }

    public CLocationResponseDTO saveLocation(CLocationRequestDTO locationRequestDTO) {
        CLocation location = new CLocation();
        location.setAddress(locationRequestDTO.getAddress());
        location.setDescription(locationRequestDTO.getDescription());
        CLocation savedLocation = cLocationRepository.save(location);
        return convertToResponseDTO(savedLocation);
    }

    public CLocationResponseDTO updateLocation(long id, CLocationRequestDTO locationRequestDTO) {
        Optional<CLocation> optionalLocation = cLocationRepository.findById(id);
        if (optionalLocation.isPresent()) {
            CLocation location = optionalLocation.get();
            location.setAddress(locationRequestDTO.getAddress());
            location.setDescription(locationRequestDTO.getDescription());
            CLocation updatedLocation = cLocationRepository.save(location);
            return convertToResponseDTO(updatedLocation);
        }
        return null; // Or throw an exception as needed
    }

    public void deleteLocation(long id) {
        cLocationRepository.deleteById(id);
    }

    private CLocationResponseDTO convertToResponseDTO(CLocation location) {
        CLocationResponseDTO dto = new CLocationResponseDTO();
        dto.setLocationId(location.getLocationId());
        dto.setAddress(location.getAddress());
        dto.setDescription(location.getDescription());
        return dto;
    }
}

