package com.csse.api.controller;

import com.csse.api.dto.bin_types.BinTypesRequestDTO;
import com.csse.api.dto.bin_types.BinTypesResponseDTO;
import com.csse.api.service.BinTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bin-types")
public class BinTypesController {

    private final BinTypesService service;

    public BinTypesController(BinTypesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BinTypesResponseDTO> create(@RequestBody BinTypesRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<BinTypesResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BinTypesResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BinTypesResponseDTO> update(@PathVariable long id, @RequestBody BinTypesRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
