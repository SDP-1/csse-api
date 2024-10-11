package com.csse.api.controller;


import com.csse.api.dto.wma.WMARequestDTO;
import com.csse.api.dto.wma.WMAResponseDTO;
import com.csse.api.service.WMAService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wmas")
public class WMAController {

    private final WMAService wmaService;

    public WMAController(WMAService wmaService) {
        this.wmaService = wmaService;
    }

    @PostMapping
    public ResponseEntity<WMAResponseDTO> createWMA(@RequestBody WMARequestDTO wmaRequestDTO) {
        WMAResponseDTO createdWMA = wmaService.createWMA(wmaRequestDTO);
        return new ResponseEntity<>(createdWMA, HttpStatus.CREATED);
    }

    @GetMapping("/{authorityId}")
    public ResponseEntity<WMAResponseDTO> getWMAById(@PathVariable String authorityId) {
        return wmaService.getWMAById(authorityId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<WMAResponseDTO>> getAllWMAs() {
        List<WMAResponseDTO> wmaList = wmaService.getAllWMAs();
        return new ResponseEntity<>(wmaList, HttpStatus.OK);
    }

    @PutMapping("/{authorityId}")
    public ResponseEntity<WMAResponseDTO> updateWMA(@PathVariable String authorityId, @RequestBody WMARequestDTO wmaRequestDTO) {
        WMAResponseDTO updatedWMA = wmaService.updateWMA(authorityId, wmaRequestDTO);
        if (updatedWMA != null) {
            return new ResponseEntity<>(updatedWMA, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{authorityId}")
    public ResponseEntity<Void> deleteWMA(@PathVariable String authorityId) {
        if (wmaService.deleteWMA(authorityId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

