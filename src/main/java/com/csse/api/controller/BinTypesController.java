package com.csse.api.controller;

import com.csse.api.model.BinTypes;
import com.csse.api.service.BinTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bin-types")
public class BinTypesController {

    @Autowired
    private BinTypesService binTypesService;

    @GetMapping
    public List<BinTypes> getAllBinTypes() {
        return binTypesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BinTypes> getBinTypeById(@PathVariable Long id) {
        return binTypesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BinTypes createBinType(@RequestBody BinTypes binTypes) {
        return binTypesService.createBinType(binTypes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BinTypes> updateBinType(@PathVariable Long id, @RequestBody BinTypes binTypes) {
        return ResponseEntity.ok(binTypesService.updateBinType(id, binTypes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBinType(@PathVariable Long id) {
        binTypesService.deleteBinType(id);
        return ResponseEntity.noContent().build();
    }
}
