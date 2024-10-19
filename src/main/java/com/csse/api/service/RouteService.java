package com.csse.api.service;

import com.csse.api.dto.route.RouteRequestDTO;
import com.csse.api.dto.route.RouteResponseDTO;
import com.csse.api.exception.RouteNotFoundException;
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
                .orElseThrow(() -> new RouteNotFoundException("Route not found with id: " + id));
    }

    public RouteResponseDTO update(long id, RouteRequestDTO dto) {
        Route entity = repository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route not found with id: " + id));
        modelMapper.map(dto, entity);
        return modelMapper.map(repository.save(entity), RouteResponseDTO.class);
    }

    public void delete(long id) {
        if (!repository.existsById(id)) {
            throw new RouteNotFoundException("Route not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
