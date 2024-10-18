package com.csse.api.controller;

import com.csse.api.dto.collector_assignment.CollectorAssignmentRequestDTO;
import com.csse.api.dto.collector_assignment.CollectorAssignmentResponseDTO;
import com.csse.api.service.CollectorAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collector-assignments")
public class CollectorAssignmentController {

    @Autowired
    private CollectorAssignmentService service;

    @PostMapping
    public ResponseEntity<CollectorAssignmentResponseDTO> create(@RequestBody CollectorAssignmentRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CollectorAssignmentResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectorAssignmentResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectorAssignmentResponseDTO> update(@PathVariable long id, @RequestBody CollectorAssignmentRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
