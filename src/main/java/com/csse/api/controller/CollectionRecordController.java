package com.csse.api.controller;

import com.csse.api.dto.CollectionRecordDTO;
import com.csse.api.service.CollectionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collection-records")
public class CollectionRecordController {

    @Autowired
    private CollectionRecordService collectionRecordService;

    @PostMapping
    public ResponseEntity<CollectionRecordDTO> createCollectionRecord(@RequestBody CollectionRecordDTO collectionRecordDTO) {
        CollectionRecordDTO createdRecord = collectionRecordService.createCollectionRecord(collectionRecordDTO);
        return ResponseEntity.ok(createdRecord);
    }

    @GetMapping
    public ResponseEntity<List<CollectionRecordDTO>> getAllCollectionRecords() {
        List<CollectionRecordDTO> records = collectionRecordService.getAllCollectionRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionRecordDTO> getCollectionRecordById(@PathVariable long id) {
        return collectionRecordService.getCollectionRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionRecordDTO> updateCollectionRecord(@PathVariable long id, @RequestBody CollectionRecordDTO collectionRecordDTO) {
        CollectionRecordDTO updatedRecord = collectionRecordService.updateCollectionRecord(id, collectionRecordDTO);
        return ResponseEntity.ok(updatedRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectionRecord(@PathVariable long id) {
        collectionRecordService.deleteCollectionRecord(id);
        return ResponseEntity.noContent().build();
    }
}
