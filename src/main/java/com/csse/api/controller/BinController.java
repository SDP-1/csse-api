package com.csse.api.controller;

import com.csse.api.model.Bin;
import com.csse.api.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bins")
public class BinController {

    @Autowired
    private BinService binService;

    @GetMapping
    public List<Bin> getAllBins() {
        return binService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bin> getBinById(@PathVariable Long id) {
        return binService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bin createBin(@RequestBody Bin bin) {
        return binService.createBin(bin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bin> updateBin(@PathVariable Long id, @RequestBody Bin bin) {
        return ResponseEntity.ok(binService.updateBin(id, bin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBin(@PathVariable Long id) {
        binService.deleteBin(id);
        return ResponseEntity.noContent().build();
    }
}
