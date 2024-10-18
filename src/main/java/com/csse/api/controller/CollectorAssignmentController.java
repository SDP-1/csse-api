package com.csse.api.controller;

import com.csse.api.dto.CollectorAssignmentDTO;
import com.csse.api.service.CollectorAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collector-assignments")
public class CollectorAssignmentController {

    @Autowired
    private CollectorAssignmentService collectorAssignmentService;

    @PostMapping
    public ResponseEntity<CollectorAssignmentDTO> createCollectorAssignment(@RequestBody CollectorAssignmentDTO collectorAssignmentDTO) {
        CollectorAssignmentDTO createdAssignment = collectorAssignmentService.createCollectorAssignment(collectorAssignmentDTO);
        return ResponseEntity.ok(createdAssignment);
    }

    @GetMapping
    public ResponseEntity<List<CollectorAssignmentDTO>> getAllCollectorAssignments() {
        List<CollectorAssignmentDTO> assignments = collectorAssignmentService.getAllCollectorAssignments();
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectorAssignmentDTO> getCollectorAssignmentById(@PathVariable long id) {
        return collectorAssignmentService.getCollectorAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectorAssignmentDTO> updateCollectorAssignment(@PathVariable long id, @RequestBody CollectorAssignmentDTO collectorAssignmentDTO) {
        CollectorAssignmentDTO updatedAssignment = collectorAssignmentService.updateCollectorAssignment(id, collectorAssignmentDTO);
        return ResponseEntity.ok(updatedAssignment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectorAssignment(@PathVariable long id) {
        collectorAssignmentService.deleteCollectorAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
