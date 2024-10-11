package com.csse.api.service;

import com.csse.api.dto.collection_schedule.CollectionScheduleRequestDTO;
import com.csse.api.dto.collection_schedule.CollectionScheduleResponseDTO;
import com.csse.api.model.CollectionSchedule;
import com.csse.api.repository.CollectionScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionScheduleService {

    @Autowired
    private CollectionScheduleRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public CollectionScheduleResponseDTO create(CollectionScheduleRequestDTO dto) {
        CollectionSchedule entity = modelMapper.map(dto, CollectionSchedule.class);
        return modelMapper.map(repository.save(entity), CollectionScheduleResponseDTO.class);
    }

    public List<CollectionScheduleResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, CollectionScheduleResponseDTO.class))
                .toList();
    }

    public CollectionScheduleResponseDTO getById(long id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, CollectionScheduleResponseDTO.class))
                .orElse(null);
    }

    public CollectionScheduleResponseDTO update(long id, CollectionScheduleRequestDTO dto) {
        CollectionSchedule entity = repository.findById(id).orElseThrow();
        modelMapper.map(dto, entity);
        return modelMapper.map(repository.save(entity), CollectionScheduleResponseDTO.class);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
