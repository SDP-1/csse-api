package com.csse.api.controller;

import com.csse.api.dto.resident.ResidentRequestDTO;
import com.csse.api.dto.resident.ResidentResponseDTO;
import com.csse.api.exception.ResidentNotFoundException;
import com.csse.api.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentService service;

    @PostMapping
    public ResponseEntity<ResidentResponseDTO> create(@RequestBody ResidentRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResidentResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidentResponseDTO> getById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (ResidentNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResidentResponseDTO> update(@PathVariable long id, @RequestBody ResidentRequestDTO dto) {
        try {
            return ResponseEntity.ok(service.update(id, dto));
        } catch (ResidentNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResidentNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
