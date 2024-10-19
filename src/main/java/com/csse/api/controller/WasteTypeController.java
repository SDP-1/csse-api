package com.csse.api.controller;

import com.csse.api.dto.wasteType.WasteTypeRequestDTO;
import com.csse.api.dto.wasteType.WasteTypeResponseDTO;
import com.csse.api.exception.WasteTypeNotFoundException;
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
    public ResponseEntity<List<WasteTypeResponseDTO>> getAllWasteTypes() {
        return wasteTypeService.getAllWasteTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteTypeResponseDTO> getWasteTypeById(@PathVariable long id) {
        return wasteTypeService.getWasteTypeById(id);
    }

    @PostMapping
    public ResponseEntity<WasteTypeResponseDTO> createWasteType(@RequestBody WasteTypeRequestDTO wasteTypeRequestDTO) {
        return wasteTypeService.createWasteType(wasteTypeRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteTypeResponseDTO> updateWasteType(@PathVariable long id,
                                                                @RequestBody WasteTypeRequestDTO wasteTypeRequestDTO) {
        return wasteTypeService.updateWasteType(id, wasteTypeRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWasteType(@PathVariable long id) {
        return wasteTypeService.deleteWasteType(id);
    }
}
