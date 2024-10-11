package com.csse.api.service;

import com.csse.api.dto.wma.WMARequestDTO;
import com.csse.api.dto.wma.WMAResponseDTO;
import com.csse.api.model.WMA;
import com.csse.api.repository.WMARepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WMAService {

    private final WMARepository wmaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public WMAService(WMARepository wmaRepository, ModelMapper modelMapper) {
        this.wmaRepository = wmaRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<WMAResponseDTO> createWMA(WMARequestDTO wmaRequestDTO) {
        WMA wma = modelMapper.map(wmaRequestDTO, WMA.class);
        WMA savedWMA = wmaRepository.save(wma);
        WMAResponseDTO responseDTO = modelMapper.map(savedWMA, WMAResponseDTO.class);
        return ResponseEntity.status(201).body(responseDTO); // Return 201 Created
    }

    public ResponseEntity<List<WMAResponseDTO>> getAllWMAs() {
        List<WMA> wmaList = wmaRepository.findAll();
        List<WMAResponseDTO> responseDTOList = wmaList.stream()
                .map(wma -> modelMapper.map(wma, WMAResponseDTO.class))
                .toList();
        return ResponseEntity.ok(responseDTOList);
    }

    public ResponseEntity<WMAResponseDTO> getWMAById(Long authorityId) {
        return wmaRepository.findById(authorityId)
                .map(wma -> ResponseEntity.ok(modelMapper.map(wma, WMAResponseDTO.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<WMAResponseDTO> updateWMA(Long authorityId, WMARequestDTO wmaRequestDTO) {
        return wmaRepository.findById(authorityId)
                .map(wma -> {
                    modelMapper.map(wmaRequestDTO, wma); // Update existing WMA
                    WMA updatedWMA = wmaRepository.save(wma);
                    return ResponseEntity.ok(modelMapper.map(updatedWMA, WMAResponseDTO.class));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteWMA(Long authorityId) {
        if (wmaRepository.existsById(authorityId)) {
            wmaRepository.deleteById(authorityId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
