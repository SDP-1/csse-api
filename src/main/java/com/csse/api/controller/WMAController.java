package com.csse.api.controller;

import com.csse.api.dto.admin.AdminResponseDTO;
import com.csse.api.dto.wma.WMARequestDTO;
import com.csse.api.dto.wma.WMAResponseDTO;
import com.csse.api.service.WMAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wma")
public class WMAController {

    private final WMAService wmaService;

    @Autowired
    public WMAController(WMAService wmaService) {
        this.wmaService = wmaService;
    }

    @PostMapping
    public ResponseEntity<WMAResponseDTO> createWMA(@RequestBody WMARequestDTO wmaRequestDTO) {
        return wmaService.createWMA(wmaRequestDTO);
    }

    @GetMapping
    public ResponseEntity<List<WMAResponseDTO>> getAllWMAs() {
        return wmaService.getAllWMAs();
    }

    @GetMapping("/{authorityId}")
    public ResponseEntity<WMAResponseDTO> getWMAById(@PathVariable Long authorityId) {
        return wmaService.getWMAById(authorityId);
    }

    @PutMapping("/{authorityId}")
    public ResponseEntity<WMAResponseDTO> updateWMA(
            @PathVariable Long authorityId,
            @RequestBody WMARequestDTO wmaRequestDTO) {
        return wmaService.updateWMA(authorityId, wmaRequestDTO);
    }

    @DeleteMapping("/{authorityId}")
    public ResponseEntity<Void> deleteWMA(@PathVariable Long authorityId) {
        return wmaService.deleteWMA(authorityId);
    }
}
