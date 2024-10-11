package com.csse.api.service;


import com.csse.api.dto.wma.WMARequestDTO;
import com.csse.api.dto.wma.WMAResponseDTO;
import com.csse.api.model.WMA;
import com.csse.api.repository.WMARepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WMAService {

    private final WMARepository wmaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public WMAService(WMARepository wmaRepository, ModelMapper modelMapper) {
        this.wmaRepository = wmaRepository;
        this.modelMapper = modelMapper;
    }

    public WMAResponseDTO createWMA(WMARequestDTO wmaRequestDTO) {
        WMA wma = modelMapper.map(wmaRequestDTO, WMA.class);
        WMA savedWMA = wmaRepository.save(wma);
        return modelMapper.map(savedWMA, WMAResponseDTO.class);
    }

    public Optional<WMAResponseDTO> getWMAById(String authorityId) {
        return wmaRepository.findById(authorityId)
                .map(wma -> modelMapper.map(wma, WMAResponseDTO.class));
    }

    public List<WMAResponseDTO> getAllWMAs() {
        return wmaRepository.findAll()
                .stream()
                .map(wma -> modelMapper.map(wma, WMAResponseDTO.class))
                .collect(Collectors.toList());
    }

    public WMAResponseDTO updateWMA(String authorityId, WMARequestDTO wmaRequestDTO) {
        if (wmaRepository.existsById(authorityId)) {
            WMA wma = modelMapper.map(wmaRequestDTO, WMA.class);
            wma.setAuthorityId(authorityId); // Ensure the ID is set correctly
            WMA updatedWMA = wmaRepository.save(wma);
            return modelMapper.map(updatedWMA, WMAResponseDTO.class);
        }
        return null; // Handle this case as appropriate
    }

    public boolean deleteWMA(String authorityId) {
        if (wmaRepository.existsById(authorityId)) {
            wmaRepository.deleteById(authorityId);
            return true;
        }
        return false; // Handle this case as appropriate
    }
}
