package com.csse.api.controller;


import com.csse.api.dto.wasteType.WasteTypeRequest;
import com.csse.api.dto.wasteType.WasteTypeResponse;
import com.csse.api.service.WasteTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waste-types")
public class WasteTypeController {
    private final WasteTypeService wasteTypeService;

    public WasteTypeController(WasteTypeService wasteTypeService) {
        this.wasteTypeService = wasteTypeService;
    }

    @GetMapping
    public ResponseEntity<List<WasteTypeResponse>> getAllWasteTypes() {
        return wasteTypeService.getAllWasteTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteTypeResponse> getWasteTypeById(@PathVariable long id) {
        return wasteTypeService.getWasteTypeById(id);
    }

    @PostMapping
    public ResponseEntity<WasteTypeResponse> createWasteType(@RequestBody WasteTypeRequest wasteTypeRequest) {
        return wasteTypeService.createWasteType(wasteTypeRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteTypeResponse> updateWasteType(@PathVariable long id,
                                                             @RequestBody WasteTypeRequest wasteTypeRequest) {
        return wasteTypeService.updateWasteType(id, wasteTypeRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWasteType(@PathVariable long id) {
        return wasteTypeService.deleteWasteType(id);
    }
}
