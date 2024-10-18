package com.csse.api.service;

import com.csse.api.dto.SpecialWasteRequestRequestDTO;
import com.csse.api.dto.SpecialWasteRequestResponseDTO;
import com.csse.api.model.SpecialWasteRequest;
import com.csse.api.repository.SpecialWasteRequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialWasteRequestService {

    @Autowired
    private SpecialWasteRequestRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public SpecialWasteRequestResponseDTO create(SpecialWasteRequestRequestDTO dto) {
        SpecialWasteRequest entity = modelMapper.map(dto, SpecialWasteRequest.class);
        return modelMapper.map(repository.save(entity), SpecialWasteRequestResponseDTO.class);
    }

    public List<SpecialWasteRequestResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, SpecialWasteRequestResponseDTO.class))
                .toList();
    }

    public SpecialWasteRequestResponseDTO getById(long id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, SpecialWasteRequestResponseDTO.class))
                .orElse(null);
    }

    public SpecialWasteRequestResponseDTO update(long id, SpecialWasteRequestRequestDTO dto) {
        SpecialWasteRequest entity = repository.findById(id).orElseThrow();
        modelMapper.map(dto, entity);
        return modelMapper.map(repository.save(entity), SpecialWasteRequestResponseDTO.class);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
