package com.csse.api.controller;


import com.csse.api.dto.croute.CRouteRequestDTO;
import com.csse.api.dto.croute.CRouteResponseDTO;
import com.csse.api.service.CRouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/croutes")
public class CRouteController {

    private final CRouteService cRouteService;

    public CRouteController(CRouteService cRouteService) {
        this.cRouteService = cRouteService;
    }

    @PostMapping
    public ResponseEntity<CRouteResponseDTO> createRoute(@RequestBody CRouteRequestDTO cRouteRequestDTO) {
        CRouteResponseDTO cRouteResponseDTO = cRouteService.createRoute(cRouteRequestDTO);
        return new ResponseEntity<>(cRouteResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CRouteResponseDTO>> getAllRoutes() {
        List<CRouteResponseDTO> routes = cRouteService.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CRouteResponseDTO> getRouteById(@PathVariable Long id) {
        return cRouteService.getRouteById(id)
                .map(routeResponseDTO -> new ResponseEntity<>(routeResponseDTO, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CRouteResponseDTO> updateRoute(@PathVariable Long id,
                                                         @RequestBody CRouteRequestDTO cRouteRequestDTO) {
        CRouteResponseDTO updatedRoute = cRouteService.updateRoute(id, cRouteRequestDTO);
        return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        cRouteService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}
