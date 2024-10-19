package com.csse.api.service;

import com.csse.api.dto.wasteType.WasteTypeRequestDTO;
import com.csse.api.dto.wasteType.WasteTypeResponseDTO;
import com.csse.api.exception.WasteTypeNotFoundException;
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

    public ResponseEntity<List<WasteTypeResponseDTO>> getAllWasteTypes() {
        List<WasteType> wasteTypes = wasteTypeRepository.findAll();
        List<WasteTypeResponseDTO> wasteTypeResponseDTOS = wasteTypes.stream()
                .map(wasteType -> modelMapper.map(wasteType, WasteTypeResponseDTO.class))
                .toList();
        return new ResponseEntity<>(wasteTypeResponseDTOS, HttpStatus.OK);
    }

    public ResponseEntity<WasteTypeResponseDTO> getWasteTypeById(long id) {
        WasteType wasteType = wasteTypeRepository.findById(id)
                .orElseThrow(() -> new WasteTypeNotFoundException("Waste type not found with ID: " + id));
        WasteTypeResponseDTO wasteTypeResponseDTO = modelMapper.map(wasteType, WasteTypeResponseDTO.class);
        return new ResponseEntity<>(wasteTypeResponseDTO, HttpStatus.OK);
    }

    public ResponseEntity<WasteTypeResponseDTO> createWasteType(WasteTypeRequestDTO wasteTypeRequestDTO) {
        WasteType wasteType = modelMapper.map(wasteTypeRequestDTO, WasteType.class);
        WasteType savedWasteType = wasteTypeRepository.save(wasteType);
        WasteTypeResponseDTO wasteTypeResponseDTO = modelMapper.map(savedWasteType, WasteTypeResponseDTO.class);
        return new ResponseEntity<>(wasteTypeResponseDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<WasteTypeResponseDTO> updateWasteType(long id, WasteTypeRequestDTO wasteTypeRequestDTO) {
        if (!wasteTypeRepository.existsById(id)) {
            throw new WasteTypeNotFoundException("Waste type not found with ID: " + id);
        }
        WasteType wasteType = modelMapper.map(wasteTypeRequestDTO, WasteType.class);
        wasteType.setId(id);
        WasteType updatedWasteType = wasteTypeRepository.save(wasteType);
        WasteTypeResponseDTO wasteTypeResponseDTO = modelMapper.map(updatedWasteType, WasteTypeResponseDTO.class);
        return new ResponseEntity<>(wasteTypeResponseDTO, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteWasteType(long id) {
        if (!wasteTypeRepository.existsById(id)) {
            throw new WasteTypeNotFoundException("Waste type not found with ID: " + id);
        }
        wasteTypeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
