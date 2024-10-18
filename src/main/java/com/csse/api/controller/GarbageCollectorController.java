package com.csse.api.controller;

import com.csse.api.dto.GarbageCollectorDTO;
import com.csse.api.service.GarbageCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garbage-collectors")
public class GarbageCollectorController {

    @Autowired
    private GarbageCollectorService garbageCollectorService;

    @PostMapping
    public ResponseEntity<GarbageCollectorDTO> createGarbageCollector(@RequestBody GarbageCollectorDTO garbageCollectorDTO) {
        GarbageCollectorDTO createdCollector = garbageCollectorService.createGarbageCollector(garbageCollectorDTO);
        return ResponseEntity.ok(createdCollector);
    }

    @GetMapping
    public ResponseEntity<List<GarbageCollectorDTO>> getAllGarbageCollectors() {
        List<GarbageCollectorDTO> collectors = garbageCollectorService.getAllGarbageCollectors();
        return ResponseEntity.ok(collectors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarbageCollectorDTO> getGarbageCollectorById(@PathVariable long id) {
        return garbageCollectorService.getGarbageCollectorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GarbageCollectorDTO> updateGarbageCollector(@PathVariable long id, @RequestBody GarbageCollectorDTO garbageCollectorDTO) {
        GarbageCollectorDTO updatedCollector = garbageCollectorService.updateGarbageCollector(id, garbageCollectorDTO);
        return ResponseEntity.ok(updatedCollector);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGarbageCollector(@PathVariable long id) {
        garbageCollectorService.deleteGarbageCollector(id);
        return ResponseEntity.noContent().build();
    }
}
