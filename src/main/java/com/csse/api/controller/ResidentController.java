package com.csse.api.controller;

import com.csse.api.dto.ResidentDTO;
import com.csse.api.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @PostMapping
    public ResponseEntity<ResidentDTO> createResident(@RequestBody ResidentDTO residentDTO) {
        ResidentDTO createdResident = residentService.createResident(residentDTO);
        return ResponseEntity.ok(createdResident);
    }

    @GetMapping
    public ResponseEntity<List<ResidentDTO>> getAllResidents() {
        List<ResidentDTO> residents = residentService.getAllResidents();
        return ResponseEntity.ok(residents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidentDTO> getResidentById(@PathVariable long id) {
        return residentService.getResidentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResidentDTO> updateResident(@PathVariable long id, @RequestBody ResidentDTO residentDTO) {
        ResidentDTO updatedResident = residentService.updateResident(id, residentDTO);
        return ResponseEntity.ok(updatedResident);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResident(@PathVariable long id) {
        residentService.deleteResident(id);
        return ResponseEntity.noContent().build();
    }
}
