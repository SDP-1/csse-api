package com.csse.api.service;

import com.csse.api.dto.resident.ResidentRequestDTO;
import com.csse.api.dto.resident.ResidentResponseDTO;
import com.csse.api.model.Resident;
import com.csse.api.repository.ResidentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public ResidentResponseDTO create(ResidentRequestDTO dto) {
        Resident entity = modelMapper.map(dto, Resident.class);
        return modelMapper.map(repository.save(entity), ResidentResponseDTO.class);
    }

    public List<ResidentResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, ResidentResponseDTO.class))
                .toList();
    }

    public ResidentResponseDTO getById(long id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, ResidentResponseDTO.class))
                .orElse(null);
    }

    public ResidentResponseDTO update(long id, ResidentRequestDTO dto) {
        Resident entity = repository.findById(id).orElseThrow();
        modelMapper.map(dto, entity);
        return modelMapper.map(repository.save(entity), ResidentResponseDTO.class);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
