package com.csse.api.service;

import com.csse.api.dto.collection_schedule.CollectionScheduleRequestDTO;
import com.csse.api.dto.collection_schedule.CollectionScheduleResponseDTO;
import com.csse.api.exception.CollectionScheduleNotFoundException;
import com.csse.api.model.CollectionSchedule;
import com.csse.api.model.Route;
import com.csse.api.repository.CollectionScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionScheduleService {

    @Autowired
    private CollectionScheduleRepository repository;

    public CollectionScheduleResponseDTO create(CollectionScheduleRequestDTO dto) {
        CollectionSchedule entity = new CollectionSchedule();
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setFrequency(dto.getFrequency());

        Route route = new Route();
        route.setRouteId(dto.getRouteId());
        entity.setRoute(route);

        return mapToResponseDTO(repository.save(entity));
    }

    public List<CollectionScheduleResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public CollectionScheduleResponseDTO getById(long id) {
        return repository.findById(id)
                .map(this::mapToResponseDTO)
                .orElseThrow(() -> new CollectionScheduleNotFoundException("Collection schedule with id " + id + " not found"));
    }

    public CollectionScheduleResponseDTO update(long id, CollectionScheduleRequestDTO dto) {
        CollectionSchedule entity = repository.findById(id)
                .orElseThrow(() -> new CollectionScheduleNotFoundException("Collection schedule with id " + id + " not found"));

        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setFrequency(dto.getFrequency());

        Route route = new Route();
        route.setRouteId(dto.getRouteId());
        entity.setRoute(route);

        return mapToResponseDTO(repository.save(entity));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    private CollectionScheduleResponseDTO mapToResponseDTO(CollectionSchedule entity) {
        CollectionScheduleResponseDTO responseDTO = new CollectionScheduleResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setStartDate(entity.getStartDate());
        responseDTO.setEndDate(entity.getEndDate());
        responseDTO.setFrequency(entity.getFrequency());
        responseDTO.setRouteId(entity.getRoute().getRouteId()); // Ensure the Route is not null
        return responseDTO;
    }
}
