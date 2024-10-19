package com.csse.api.service;

import com.csse.api.dto.business.BusinessRequestDTO;
import com.csse.api.dto.business.BusinessResponseDTO;
import com.csse.api.exception.BusinessNotFoundException;
import com.csse.api.model.Business;
import com.csse.api.model.WasteType;
import com.csse.api.repository.BusinessRepository;
import com.csse.api.repository.WasteTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;

    private final WasteTypeRepository wasteTypeRepository;

    public BusinessService(BusinessRepository businessRepository, WasteTypeRepository wasteTypeRepository) {
        this.businessRepository = businessRepository;
        this.wasteTypeRepository = wasteTypeRepository;
    }

    public BusinessResponseDTO create(BusinessRequestDTO dto) {
        Business business = new Business();
        business.setName(dto.getName());
        business.setAddress(dto.getAddress());
        business.setResidentialType(dto.getResidentialType());
        business.setBusinessType(dto.getBusinessType());
        business.setBusinessRegistration(dto.getBusinessRegistration());

        if (dto.getWasteTypeIds() != null) {
            List<WasteType> wasteTypes = wasteTypeRepository.findAllById(dto.getWasteTypeIds());
            business.setWasteTypes(wasteTypes);
        }

        business = businessRepository.save(business);
        return mapToResponseDTO(business);
    }

    public List<BusinessResponseDTO> getAll() {
        return businessRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public BusinessResponseDTO getById(long id) {
        return businessRepository.findById(id)
                .map(this::mapToResponseDTO)
                .orElseThrow(() -> new BusinessNotFoundException("Business not found with id: " + id));
    }

    public BusinessResponseDTO update(long id, BusinessRequestDTO dto) {
        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new BusinessNotFoundException("Business not found with id: " + id));

        // Update business details
        business.setName(dto.getName());
        business.setAddress(dto.getAddress());
        business.setResidentialType(dto.getResidentialType());
        business.setBusinessType(dto.getBusinessType());
        business.setBusinessRegistration(dto.getBusinessRegistration());

        // Handle waste type updates
        if (dto.getWasteTypeIds() != null && !dto.getWasteTypeIds().isEmpty()) {
            List<WasteType> wasteTypes = wasteTypeRepository.findAllById(dto.getWasteTypeIds());
            business.setWasteTypes(wasteTypes);
        } else {
            business.setWasteTypes(Collections.emptyList()); // Optionally clear existing types
        }

        business = businessRepository.save(business);
        return mapToResponseDTO(business);
    }

    public void delete(long id) {
        businessRepository.deleteById(id);
    }

    private BusinessResponseDTO mapToResponseDTO(Business business) {
        BusinessResponseDTO dto = new BusinessResponseDTO();
        dto.setId(business.getId());
        dto.setName(business.getName());
        dto.setAddress(business.getAddress());
        dto.setResidentialType(business.getResidentialType());
        dto.setBusinessType(business.getBusinessType());
        dto.setBusinessRegistration(business.getBusinessRegistration());

        // Check for null and initialize to an empty list if necessary
        List<Long> wasteTypeIds = (business.getWasteTypes() != null) ?
                business.getWasteTypes().stream().map(WasteType::getId).collect(Collectors.toList()) :
                Collections.emptyList();
        dto.setWasteTypeIds(wasteTypeIds);

        return dto;
    }

}
