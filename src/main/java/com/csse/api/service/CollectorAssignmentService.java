package com.csse.api.service;

import com.csse.api.dto.CollectorAssignmentDTO;
import com.csse.api.model.CollectorAssignment;
import com.csse.api.repository.CollectorAssignmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectorAssignmentService {

    @Autowired
    private CollectorAssignmentRepository collectorAssignmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CollectorAssignmentDTO createCollectorAssignment(CollectorAssignmentDTO collectorAssignmentDTO) {
        CollectorAssignment collectorAssignment = modelMapper.map(collectorAssignmentDTO, CollectorAssignment.class);
        collectorAssignment = collectorAssignmentRepository.save(collectorAssignment);
        return modelMapper.map(collectorAssignment, CollectorAssignmentDTO.class);
    }

    public List<CollectorAssignmentDTO> getAllCollectorAssignments() {
        List<CollectorAssignment> assignments = collectorAssignmentRepository.findAll();
        return assignments.stream()
                .map(assignment -> modelMapper.map(assignment, CollectorAssignmentDTO.class))
                .toList();
    }

    public Optional<CollectorAssignmentDTO> getCollectorAssignmentById(long id) {
        return collectorAssignmentRepository.findById(id)
                .map(assignment -> modelMapper.map(assignment, CollectorAssignmentDTO.class));
    }

    public CollectorAssignmentDTO updateCollectorAssignment(long id, CollectorAssignmentDTO collectorAssignmentDTO) {
        CollectorAssignment collectorAssignment = collectorAssignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Collector Assignment not found"));
        modelMapper.map(collectorAssignmentDTO, collectorAssignment);
        collectorAssignment = collectorAssignmentRepository.save(collectorAssignment);
        return modelMapper.map(collectorAssignment, CollectorAssignmentDTO.class);
    }

    public void deleteCollectorAssignment(long id) {
        collectorAssignmentRepository.deleteById(id);
    }
}
