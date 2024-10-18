package com.csse.api.controller;

import com.csse.api.dto.BusinessDTO;
import com.csse.api.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping
    public ResponseEntity<BusinessDTO> createBusiness(@RequestBody BusinessDTO businessDTO) {
        BusinessDTO createdBusiness = businessService.createBusiness(businessDTO);
        return ResponseEntity.ok(createdBusiness);
    }

    @GetMapping
    public ResponseEntity<List<BusinessDTO>> getAllBusinesses() {
        List<BusinessDTO> businesses = businessService.getAllBusinesses();
        return ResponseEntity.ok(businesses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessDTO> getBusinessById(@PathVariable long id) {
        return businessService.getBusinessById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessDTO> updateBusiness(@PathVariable long id, @RequestBody BusinessDTO businessDTO) {
        BusinessDTO updatedBusiness = businessService.updateBusiness(id, businessDTO);
        return ResponseEntity.ok(updatedBusiness);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable long id) {
        businessService.deleteBusiness(id);
        return ResponseEntity.noContent().build();
    }
}
