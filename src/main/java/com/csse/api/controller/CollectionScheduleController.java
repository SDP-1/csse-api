package com.csse.api.controller;

import com.csse.api.dto.collection_schedule.CollectionScheduleRequestDTO;
import com.csse.api.dto.collection_schedule.CollectionScheduleResponseDTO;
import com.csse.api.exception.CollectionScheduleNotFoundException;
import com.csse.api.service.CollectionScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collection-schedules")
public class CollectionScheduleController {

    private final CollectionScheduleService collectionScheduleService;

    public CollectionScheduleController(CollectionScheduleService collectionScheduleService) {
        this.collectionScheduleService = collectionScheduleService;
    }

    @PostMapping
    public ResponseEntity<CollectionScheduleResponseDTO> create(@RequestBody CollectionScheduleRequestDTO requestDTO) {
        CollectionScheduleResponseDTO responseDTO = collectionScheduleService.create(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CollectionScheduleResponseDTO>> getAll() {
        List<CollectionScheduleResponseDTO> schedules = collectionScheduleService.getAll();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionScheduleResponseDTO> getById(@PathVariable long id) {
        CollectionScheduleResponseDTO responseDTO = collectionScheduleService.getById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionScheduleResponseDTO> update(@PathVariable long id, @RequestBody CollectionScheduleRequestDTO requestDTO) {
        try {
            CollectionScheduleResponseDTO responseDTO = collectionScheduleService.update(id, requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (CollectionScheduleNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        collectionScheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
