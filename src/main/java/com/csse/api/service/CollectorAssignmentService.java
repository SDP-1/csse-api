package com.csse.api.service;

import com.csse.api.dto.collector_assignment.CollectorAssignmentRequestDTO;
import com.csse.api.dto.collector_assignment.CollectorAssignmentResponseDTO;
import com.csse.api.exception.CollectorAssignmentNotFoundException;
import com.csse.api.model.CollectionSchedule;
import com.csse.api.model.CollectorAssignment;
import com.csse.api.model.GarbageCollector;
import com.csse.api.repository.CollectorAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectorAssignmentService {

    @Autowired
    private CollectorAssignmentRepository repository;

    public CollectorAssignmentResponseDTO create(CollectorAssignmentRequestDTO dto) {
        CollectorAssignment entity = new CollectorAssignment();
        entity.setCollectionSchedule(new CollectionSchedule(dto.getCollectionScheduleId())); // Assuming constructor or setter
        entity.setCollector(new GarbageCollector(dto.getCollectorId())); // Assuming constructor or setter
        entity.setAssignmentDate(dto.getAssignedDate());

        CollectorAssignment savedEntity = repository.save(entity);
        return toResponseDTO(savedEntity);
    }

    public List<CollectorAssignmentResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CollectorAssignmentResponseDTO getById(long id) {
        CollectorAssignment entity = repository.findById(id)
                .orElseThrow(() -> new CollectorAssignmentNotFoundException("Collector assignment with id " + id + " not found"));
        return toResponseDTO(entity);
    }

    public CollectorAssignmentResponseDTO update(long id, CollectorAssignmentRequestDTO dto) {
        CollectorAssignment entity = repository.findById(id)
                .orElseThrow(() -> new CollectorAssignmentNotFoundException("Collector assignment with id " + id + " not found"));

        entity.setCollectionSchedule(new CollectionSchedule(dto.getCollectionScheduleId())); // Assuming constructor or setter
        entity.setCollector(new GarbageCollector(dto.getCollectorId())); // Assuming constructor or setter
        entity.setAssignmentDate(dto.getAssignedDate());

        CollectorAssignment savedEntity = repository.save(entity);
        return toResponseDTO(savedEntity);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    private CollectorAssignmentResponseDTO toResponseDTO(CollectorAssignment entity) {
        CollectorAssignmentResponseDTO dto = new CollectorAssignmentResponseDTO();
        dto.setId(entity.getId());
        dto.setCollectionScheduleId(entity.getCollectionSchedule().getId()); // Assuming getId() exists
        dto.setCollectorId(entity.getCollector().getId()); // Assuming getId() exists
        dto.setAssignedDate(entity.getAssignmentDate());
        return dto;
    }
}
