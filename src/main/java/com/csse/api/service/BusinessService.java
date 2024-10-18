package com.csse.api.service;

import com.csse.api.dto.business.BusinessRequestDTO;
import com.csse.api.dto.business.BusinessResponseDTO;
import com.csse.api.model.Business;
import com.csse.api.model.WasteType;
import com.csse.api.repository.BusinessRepository;
import com.csse.api.repository.WasteTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private WasteTypeRepository wasteTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BusinessResponseDTO create(BusinessRequestDTO dto) {
        Business business = modelMapper.map(dto, Business.class);
        if (dto.getWasteTypeIds() != null) {
            List<WasteType> wasteTypes = wasteTypeRepository.findAllById(dto.getWasteTypeIds());
            business.setWasteTypes(wasteTypes);
        }
        return modelMapper.map(businessRepository.save(business), BusinessResponseDTO.class);
    }

    public List<BusinessResponseDTO> getAll() {
        return businessRepository.findAll().stream()
                .map(business -> modelMapper.map(business, BusinessResponseDTO.class))
                .collect(Collectors.toList());
    }

    public BusinessResponseDTO getById(long id) {
        return businessRepository.findById(id)
                .map(business -> modelMapper.map(business, BusinessResponseDTO.class))
                .orElse(null);
    }

    public BusinessResponseDTO update(long id, BusinessRequestDTO dto) {
        Business business = businessRepository.findById(id).orElseThrow();
        modelMapper.map(dto, business);
        if (dto.getWasteTypeIds() != null) {
            List<WasteType> wasteTypes = wasteTypeRepository.findAllById(dto.getWasteTypeIds());
            business.setWasteTypes(wasteTypes);
        }
        return modelMapper.map(businessRepository.save(business), BusinessResponseDTO.class);
    }

    public void delete(long id) {
        businessRepository.deleteById(id);
    }
}
