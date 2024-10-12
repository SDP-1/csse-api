package com.csse.api.service;


import com.csse.api.dto.croute.CRouteRequestDTO;
import com.csse.api.dto.croute.CRouteResponseDTO;
import com.csse.api.model.CRoute;
import com.csse.api.repository.CRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CRouteService {

    private final CRouteRepository cRouteRepository;

    @Autowired
    public CRouteService(CRouteRepository cRouteRepository) {
        this.cRouteRepository = cRouteRepository;
    }

    public CRouteResponseDTO createRoute(CRouteRequestDTO cRouteRequestDTO) {
        CRoute cRoute = new CRoute();
        cRoute.setRouteName(cRouteRequestDTO.getRouteName());
        cRoute.setRouteDescription(cRouteRequestDTO.getRouteDescription());
        cRoute.setStartLocation(cRouteRequestDTO.getStartLocation());
        cRoute.setEndLocation(cRouteRequestDTO.getEndLocation());
        cRoute.setArea(cRouteRequestDTO.getArea());
        cRoute.setLastOptimizedDate(cRouteRequestDTO.getLastOptimizedDate());
        cRoute.setCollectors(cRouteRequestDTO.getCollectors());

        CRoute savedRoute = cRouteRepository.save(cRoute);
        return convertToResponseDTO(savedRoute);
    }

    public List<CRouteResponseDTO> getAllRoutes() {
        List<CRoute> routes = cRouteRepository.findAll();
        return routes.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public Optional<CRouteResponseDTO> getRouteById(Long id) {
        return cRouteRepository.findById(id)
                .map(this::convertToResponseDTO);
    }

    public CRouteResponseDTO updateRoute(Long id, CRouteRequestDTO cRouteRequestDTO) {
        CRoute routeToUpdate = cRouteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route with id " + id + " not found"));

        routeToUpdate.setRouteName(cRouteRequestDTO.getRouteName());
        routeToUpdate.setRouteDescription(cRouteRequestDTO.getRouteDescription());
        routeToUpdate.setStartLocation(cRouteRequestDTO.getStartLocation());
        routeToUpdate.setEndLocation(cRouteRequestDTO.getEndLocation());
        routeToUpdate.setArea(cRouteRequestDTO.getArea());
        routeToUpdate.setLastOptimizedDate(cRouteRequestDTO.getLastOptimizedDate());
        routeToUpdate.setCollectors(cRouteRequestDTO.getCollectors());

        CRoute updatedRoute = cRouteRepository.save(routeToUpdate);
        return convertToResponseDTO(updatedRoute);
    }

    public void deleteRoute(Long id) {
        cRouteRepository.deleteById(id);
    }

    private CRouteResponseDTO convertToResponseDTO(CRoute cRoute) {
        return new CRouteResponseDTO(
                cRoute.getRouteId(),
                cRoute.getRouteName(),
                cRoute.getRouteDescription(),
                cRoute.getStartLocation(),
                cRoute.getEndLocation(),
                cRoute.getArea(),
                cRoute.getLastOptimizedDate(),
                cRoute.getCollectors()
        );
    }
}
