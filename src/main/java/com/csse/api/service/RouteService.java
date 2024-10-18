package com.csse.api.service;

import com.csse.api.dto.route.RouteRequestDTO;
import com.csse.api.dto.route.RouteResponseDTO;
import com.csse.api.model.Route;
import com.csse.api.repository.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public RouteResponseDTO create(RouteRequestDTO dto) {
        Route entity = modelMapper.map(dto, Route.class);
        return modelMapper.map(repository.save(entity), RouteResponseDTO.class);
    }

    public List<RouteResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, RouteResponseDTO.class))
                .toList();
    }

    public RouteResponseDTO getById(long id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, RouteResponseDTO.class))
                .orElse(null);
    }

    public RouteResponseDTO update(long id, RouteRequestDTO dto) {
        Route entity = repository.findById(id).orElseThrow();
        modelMapper.map(dto, entity);
        return modelMapper.map(repository.save(entity), RouteResponseDTO.class);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
