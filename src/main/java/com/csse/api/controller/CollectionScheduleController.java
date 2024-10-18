package com.csse.api.controller;

import com.csse.api.dto.CollectionScheduleDTO;
import com.csse.api.service.CollectionScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collection-schedules")
public class CollectionScheduleController {

    @Autowired
    private CollectionScheduleService collectionScheduleService;

    @PostMapping
    public ResponseEntity<CollectionScheduleDTO> createCollectionSchedule(@RequestBody CollectionScheduleDTO collectionScheduleDTO) {
        CollectionScheduleDTO createdSchedule = collectionScheduleService.createCollectionSchedule(collectionScheduleDTO);
        return ResponseEntity.ok(createdSchedule);
    }

    @GetMapping
    public ResponseEntity<List<CollectionScheduleDTO>> getAllCollectionSchedules() {
        List<CollectionScheduleDTO> schedules = collectionScheduleService.getAllCollectionSchedules();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionScheduleDTO> getCollectionScheduleById(@PathVariable long id) {
        return collectionScheduleService.getCollectionScheduleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionScheduleDTO> updateCollectionSchedule(@PathVariable long id, @RequestBody CollectionScheduleDTO collectionScheduleDTO) {
        CollectionScheduleDTO updatedSchedule = collectionScheduleService.updateCollectionSchedule(id, collectionScheduleDTO);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectionSchedule(@PathVariable long id) {
        collectionScheduleService.deleteCollectionSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
