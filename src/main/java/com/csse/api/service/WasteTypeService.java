package com.csse.api.service;


import com.csse.api.dto.wasteType.WasteTypeRequest;
import com.csse.api.dto.wasteType.WasteTypeResponse;
import com.csse.api.model.WasteType;
import com.csse.api.repository.WasteTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteTypeService {
    private final WasteTypeRepository wasteTypeRepository;
    private final ModelMapper modelMapper;

    public WasteTypeService(WasteTypeRepository wasteTypeRepository, ModelMapper modelMapper) {
        this.wasteTypeRepository = wasteTypeRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<List<WasteTypeResponse>> getAllWasteTypes() {
        List<WasteType> wasteTypes = wasteTypeRepository.findAll();
        List<WasteTypeResponse> wasteTypeResponses = wasteTypes.stream()
                .map(wasteType -> modelMapper.map(wasteType, WasteTypeResponse.class))
                .toList();
        return new ResponseEntity<>(wasteTypeResponses, HttpStatus.OK);
    }

    public ResponseEntity<WasteTypeResponse> getWasteTypeById(long id) {
        Optional<WasteType> wasteTypeOptional = wasteTypeRepository.findById(id);
        if (wasteTypeOptional.isPresent()) {
            WasteTypeResponse wasteTypeResponse = modelMapper.map(wasteTypeOptional.get(), WasteTypeResponse.class);
            return new ResponseEntity<>(wasteTypeResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<WasteTypeResponse> createWasteType(WasteTypeRequest wasteTypeRequest) {
        WasteType wasteType = modelMapper.map(wasteTypeRequest, WasteType.class);
        WasteType savedWasteType = wasteTypeRepository.save(wasteType);
        WasteTypeResponse wasteTypeResponse = modelMapper.map(savedWasteType, WasteTypeResponse.class);
        return new ResponseEntity<>(wasteTypeResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<WasteTypeResponse> updateWasteType(long id, WasteTypeRequest wasteTypeRequest) {
        if (!wasteTypeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        WasteType wasteType = modelMapper.map(wasteTypeRequest, WasteType.class);
        wasteType.setId(id); // Ensure the ID is set for update
        WasteType updatedWasteType = wasteTypeRepository.save(wasteType);
        WasteTypeResponse wasteTypeResponse = modelMapper.map(updatedWasteType, WasteTypeResponse.class);
        return new ResponseEntity<>(wasteTypeResponse, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteWasteType(long id) {
        if (!wasteTypeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        wasteTypeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
