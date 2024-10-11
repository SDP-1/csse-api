package com.csse.api.service;

import com.csse.api.dto.collector_assignment.CollectorAssignmentRequestDTO;
import com.csse.api.dto.collector_assignment.CollectorAssignmentResponseDTO;
import com.csse.api.model.CollectorAssignment;
import com.csse.api.repository.CollectorAssignmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectorAssignmentService {

    @Autowired
    private CollectorAssignmentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public CollectorAssignmentResponseDTO create(CollectorAssignmentRequestDTO dto) {
        CollectorAssignment entity = modelMapper.map(dto, CollectorAssignment.class);
        return modelMapper.map(repository.save(entity), CollectorAssignmentResponseDTO.class);
    }

    public List<CollectorAssignmentResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, CollectorAssignmentResponseDTO.class))
                .toList();
    }

    public com.csse.api.dto.collector_assignment.CollectorAssignmentResponseDTO getById(long id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, CollectorAssignmentResponseDTO.class))
                .orElse(null);
    }

    public com.csse.api.dto.collector_assignment.CollectorAssignmentResponseDTO update(long id, CollectorAssignmentRequestDTO dto) {
        CollectorAssignment entity = repository.findById(id).orElseThrow();
        modelMapper.map(dto, entity);
        return modelMapper.map(repository.save(entity), CollectorAssignmentResponseDTO.class);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
