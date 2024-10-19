package com.csse.api.service;

import com.csse.api.dto.route.RouteRequestDTO;
import com.csse.api.dto.route.RouteResponseDTO;
import com.csse.api.exception.RouteNotFoundException;
import com.csse.api.model.Route;
import com.csse.api.repository.RouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @InjectMocks
    private RouteService routeService;

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private ModelMapper modelMapper;

    private RouteRequestDTO routeRequestDTO;
    private Route route;
    private RouteResponseDTO routeResponseDTO;

    @BeforeEach
    void setUp() {
        routeRequestDTO = new RouteRequestDTO("Route 1", "Description", "Start", "End", "Area", null, 1L);
        route = new Route(1L, "Route 1", "Description", "Start", "End", "Area", null, null, null);
        routeResponseDTO = new RouteResponseDTO(1L, "Route 1", "Description", "Start", "End", "Area", null, 1L);
    }

    @Test
    void testCreate() {
        when(modelMapper.map(routeRequestDTO, Route.class)).thenReturn(route);
        when(routeRepository.save(route)).thenReturn(route);
        when(modelMapper.map(route, RouteResponseDTO.class)).thenReturn(routeResponseDTO);

        RouteResponseDTO response = routeService.create(routeRequestDTO);

        assertNotNull(response);
        assertEquals(routeResponseDTO.getRouteId(), response.getRouteId());
        verify(routeRepository, times(1)).save(any(Route.class));
    }

    @Test
    void testGetByIdFound() {
        when(routeRepository.findById(anyLong())).thenReturn(Optional.of(route));
        when(modelMapper.map(route, RouteResponseDTO.class)).thenReturn(routeResponseDTO);

        RouteResponseDTO response = routeService.getById(1L);

        assertNotNull(response);
        assertEquals(routeResponseDTO.getRouteId(), response.getRouteId());
    }

    @Test
    void testGetByIdNotFound() {
        when(routeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RouteNotFoundException.class, () -> {
            routeService.getById(1L);
        });

        assertEquals("Route not found with id: 1", exception.getMessage());
    }

    @Test
    void testUpdateFound() {
        when(routeRepository.findById(anyLong())).thenReturn(Optional.of(route));
        when(modelMapper.map(routeRequestDTO, Route.class)).thenReturn(route); // Corrected here
        when(routeRepository.save(route)).thenReturn(route);
        when(modelMapper.map(route, RouteResponseDTO.class)).thenReturn(routeResponseDTO);

        RouteResponseDTO response = routeService.update(1L, routeRequestDTO);

        assertNotNull(response);
        assertEquals(routeResponseDTO.getRouteId(), response.getRouteId());
        verify(routeRepository, times(1)).save(any(Route.class));
    }


    @Test
    void testUpdateNotFound() {
        when(routeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RouteNotFoundException.class, () -> {
            routeService.update(1L, routeRequestDTO);
        });

        assertEquals("Route not found with id: 1", exception.getMessage());
    }

    @Test
    void testDeleteFound() {
        when(routeRepository.existsById(anyLong())).thenReturn(true);

        routeService.delete(1L);

        verify(routeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteNotFound() {
        when(routeRepository.existsById(anyLong())).thenReturn(false);

        Exception exception = assertThrows(RouteNotFoundException.class, () -> {
            routeService.delete(1L);
        });

        assertEquals("Route not found with id: 1", exception.getMessage());
    }
}
