package com.csse.api.controller;

import com.csse.api.dto.GarbageCollectorRequestDTO;
import com.csse.api.dto.GarbageCollectorResponseDTO;
import com.csse.api.service.GarbageCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garbage-collectors")
public class GarbageCollectorController {

    @Autowired
    private GarbageCollectorService service;

    @PostMapping
    public ResponseEntity<GarbageCollectorResponseDTO> create(@RequestBody GarbageCollectorRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<GarbageCollectorResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarbageCollectorResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GarbageCollectorResponseDTO> update(@PathVariable long id, @RequestBody GarbageCollectorRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
