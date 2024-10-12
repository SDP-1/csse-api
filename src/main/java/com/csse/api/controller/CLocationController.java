package com.csse.api.controller;

import com.csse.api.dto.clocation.CLocationRequestDTO;
import com.csse.api.dto.clocation.CLocationResponseDTO;
import com.csse.api.service.CLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clocations")
public class CLocationController {
    @Autowired
    private CLocationService cLocationService;

    @GetMapping
    public ResponseEntity<List<CLocationResponseDTO>> getAllLocations() {
        List<CLocationResponseDTO> locations = cLocationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CLocationResponseDTO> getLocationById(@PathVariable long id) {
        CLocationResponseDTO location = cLocationService.getLocationById(id);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(location);
    }

    @PostMapping
    public ResponseEntity<CLocationResponseDTO> createLocation(@RequestBody CLocationRequestDTO locationRequestDTO) {
        CLocationResponseDTO createdLocation = cLocationService.saveLocation(locationRequestDTO);
        return ResponseEntity.status(201).body(createdLocation);
    }

    @PutMapping("/{id}")  // Added update mapping
    public ResponseEntity<CLocationResponseDTO> updateLocation(@PathVariable long id, @RequestBody CLocationRequestDTO locationRequestDTO) {
        CLocationResponseDTO updatedLocation = cLocationService.updateLocation(id, locationRequestDTO);
        if (updatedLocation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable long id) {
        cLocationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
