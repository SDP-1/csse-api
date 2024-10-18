package com.csse.api.controller;

import com.csse.api.dto.collection_record.CollectionRecordRequestDTO;
import com.csse.api.dto.collection_record.CollectionRecordResponseDTO;
import com.csse.api.service.CollectionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collection-records")
public class CollectionRecordController {

    @Autowired
    private CollectionRecordService service;

    @PostMapping
    public ResponseEntity<CollectionRecordResponseDTO> create(@RequestBody CollectionRecordRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CollectionRecordResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionRecordResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionRecordResponseDTO> update(@PathVariable long id, @RequestBody CollectionRecordRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
