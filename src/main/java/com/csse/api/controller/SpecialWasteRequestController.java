package com.csse.api.controller;

import com.csse.api.dto.SpecialWasteRequestRequestDTO;
import com.csse.api.dto.SpecialWasteRequestResponseDTO;
import com.csse.api.service.SpecialWasteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/special-waste-requests")
public class SpecialWasteRequestController {

    @Autowired
    private SpecialWasteRequestService service;

    @PostMapping
    public ResponseEntity<SpecialWasteRequestResponseDTO> create(@RequestBody SpecialWasteRequestRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<SpecialWasteRequestResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialWasteRequestResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialWasteRequestResponseDTO> update(@PathVariable long id, @RequestBody SpecialWasteRequestRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
