package com.csse.api.controller;

import com.csse.api.dto.route.RouteRequestDTO;
import com.csse.api.dto.route.RouteResponseDTO;
import com.csse.api.exception.RouteNotFoundException;
import com.csse.api.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService service;

    @PostMapping
    public ResponseEntity<RouteResponseDTO> create(@RequestBody RouteRequestDTO dto) {
        RouteResponseDTO createdRoute = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoute);
    }

    @GetMapping
    public ResponseEntity<List<RouteResponseDTO>> getAll() {
        List<RouteResponseDTO> routes = service.getAll();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteResponseDTO> getById(@PathVariable long id) {
        try {
            RouteResponseDTO route = service.getById(id);
            return ResponseEntity.ok(route);
        } catch (RouteNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteResponseDTO> update(@PathVariable long id, @RequestBody RouteRequestDTO dto) {
        try {
            RouteResponseDTO updatedRoute = service.update(id, dto);
            return ResponseEntity.ok(updatedRoute);
        } catch (RouteNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RouteNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
