package com.csse.api.service;

import com.csse.api.dto.bin_types.BinTypesRequestDTO;
import com.csse.api.dto.bin_types.BinTypesResponseDTO;
import com.csse.api.exception.BinTypeNotFoundException;
import com.csse.api.model.BinTypes;
import com.csse.api.repository.BinTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BinTypesService {

    private final BinTypesRepository repository;

    @Autowired
    public BinTypesService(BinTypesRepository repository) {
        this.repository = repository;
    }

    public BinTypesResponseDTO create(BinTypesRequestDTO dto) {
        BinTypes entity = new BinTypes();
        entity.setName(dto.getName());
        entity.setCapacity(dto.getCapacity());
        entity.setProducer(dto.getProducer());
        entity.setType(dto.getType());
        return convertToResponseDTO(repository.save(entity));
    }

    public List<BinTypesResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public BinTypesResponseDTO getById(long id) {
        BinTypes entity = repository.findById(id)
                .orElseThrow(() -> new BinTypeNotFoundException("BinType not found with id " + id));
        return convertToResponseDTO(entity);
    }

    public BinTypesResponseDTO update(long id, BinTypesRequestDTO dto) {
        BinTypes entity = repository.findById(id)
                .orElseThrow(() -> new BinTypeNotFoundException("BinType not found with id " + id));

        entity.setName(dto.getName());
        entity.setCapacity(dto.getCapacity());
        entity.setProducer(dto.getProducer());
        entity.setType(dto.getType());

        return convertToResponseDTO(repository.save(entity));
    }

    public void delete(long id) {
        if (!repository.existsById(id)) {
            throw new BinTypeNotFoundException("BinType not found with id " + id);
        }
        repository.deleteById(id);
    }

    private BinTypesResponseDTO convertToResponseDTO(BinTypes entity) {
        BinTypesResponseDTO dto = new BinTypesResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCapacity(entity.getCapacity());
        dto.setProducer(entity.getProducer());
        dto.setType(entity.getType());
        return dto;
    }
}
