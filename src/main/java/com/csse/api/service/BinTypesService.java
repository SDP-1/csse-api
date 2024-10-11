package com.csse.api.service;

import com.csse.api.dto.bin_types.BinTypesRequestDTO;
import com.csse.api.dto.bin_types.BinTypesResponseDTO;
import com.csse.api.model.BinTypes;
import com.csse.api.repository.BinTypesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BinTypesService {

    @Autowired
    private BinTypesRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public BinTypesResponseDTO create(BinTypesRequestDTO dto) {
        BinTypes entity = modelMapper.map(dto, BinTypes.class);
        return modelMapper.map(repository.save(entity), BinTypesResponseDTO.class);
    }

    public List<BinTypesResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, BinTypesResponseDTO.class))
                .collect(Collectors.toList());
    }

    public BinTypesResponseDTO getById(long id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, BinTypesResponseDTO.class))
                .orElse(null);
    }

    public BinTypesResponseDTO update(long id, BinTypesRequestDTO dto) {
        BinTypes entity = repository.findById(id).orElseThrow();
        modelMapper.map(dto, entity);
        return modelMapper.map(repository.save(entity), BinTypesResponseDTO.class);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
