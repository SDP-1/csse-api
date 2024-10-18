package com.csse.api.service;

import com.csse.api.dto.GarbageCollectorRequestDTO;
import com.csse.api.dto.GarbageCollectorResponseDTO;
import com.csse.api.model.GarbageCollector;
import com.csse.api.repository.GarbageCollectorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarbageCollectorService {

    @Autowired
    private GarbageCollectorRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public GarbageCollectorResponseDTO create(GarbageCollectorRequestDTO dto) {
        GarbageCollector entity = modelMapper.map(dto, GarbageCollector.class);
        return modelMapper.map(repository.save(entity), GarbageCollectorResponseDTO.class);
    }

    public List<GarbageCollectorResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, GarbageCollectorResponseDTO.class))
                .toList();
    }

    public GarbageCollectorResponseDTO getById(long id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, GarbageCollectorResponseDTO.class))
                .orElse(null);
    }

    public GarbageCollectorResponseDTO update(long id, GarbageCollectorRequestDTO dto) {
        GarbageCollector entity = repository.findById(id).orElseThrow();
        modelMapper.map(dto, entity);
        return modelMapper.map(repository.save(entity), GarbageCollectorResponseDTO.class);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}

