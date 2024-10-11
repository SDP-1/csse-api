package com.csse.api.controller;

import com.csse.api.model.WasteType;
import com.csse.api.service.WasteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/waste-types")
public class WasteTypeController {

    @Autowired
    private WasteTypeService wasteTypeService;

    @GetMapping
    public List<WasteType> getAllWasteTypes() {
        return wasteTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteType> getWasteTypeById(@PathVariable Long id) {
        return wasteTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<List<WasteType>> createWasteTypes(@RequestBody List<WasteType> wasteTypes) {
        List<WasteType> savedWasteTypes = wasteTypes.stream()
                .map(wasteTypeService::save)
                .collect(Collectors.toList());
        return ResponseEntity.ok(savedWasteTypes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteType> updateWasteType(@PathVariable Long id, @RequestBody WasteType wasteType) {
        return wasteTypeService.findById(id)
                .map(existingWasteType -> {
                    wasteType.setId(id);
                    return ResponseEntity.ok(wasteTypeService.save(wasteType));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWasteType(@PathVariable Long id) {
        if (wasteTypeService.findById(id).isPresent()) {
            wasteTypeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
