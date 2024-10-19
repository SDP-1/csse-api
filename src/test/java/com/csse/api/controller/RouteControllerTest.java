package com.csse.api.controller;

import com.csse.api.dto.route.RouteRequestDTO;
import com.csse.api.dto.route.RouteResponseDTO;
import com.csse.api.exception.RouteNotFoundException;
import com.csse.api.service.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteControllerTest {

    @InjectMocks
    private RouteController routeController;

    @Mock
    private RouteService routeService;

    private RouteRequestDTO routeRequestDTO;
    private RouteResponseDTO routeResponseDTO;

    @BeforeEach
    void setUp() {
        routeRequestDTO = new RouteRequestDTO("Route 1", "Description", "Start", "End", "Area", null, 1L);
        routeResponseDTO = new RouteResponseDTO(1L, "Route 1", "Description", "Start", "End", "Area", null, 1L);
    }

    @Test
    void testCreate() {
        when(routeService.create(any(RouteRequestDTO.class))).thenReturn(routeResponseDTO);

        ResponseEntity<RouteResponseDTO> response = routeController.create(routeRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(routeResponseDTO, response.getBody());
        verify(routeService, times(1)).create(routeRequestDTO);
    }

    @Test
    void testGetAll() {
        List<RouteResponseDTO> routes = Arrays.asList(routeResponseDTO);
        when(routeService.getAll()).thenReturn(routes);

        ResponseEntity<List<RouteResponseDTO>> response = routeController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(routes, response.getBody());
        verify(routeService, times(1)).getAll();
    }

    @Test
    void testGetByIdFound() {
        when(routeService.getById(anyLong())).thenReturn(routeResponseDTO);

        ResponseEntity<RouteResponseDTO> response = routeController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(routeResponseDTO, response.getBody());
        verify(routeService, times(1)).getById(1L);
    }

    @Test
    void testGetByIdNotFound() {
        when(routeService.getById(anyLong())).thenThrow(new RouteNotFoundException("Route not found with id: 1"));

        Exception exception = assertThrows(RouteNotFoundException.class, () -> {
            routeController.getById(1L);
        });

        assertEquals("Route not found with id: 1", exception.getMessage());
        verify(routeService, times(1)).getById(1L);
    }

    @Test
    void testUpdateFound() {
        when(routeService.update(anyLong(), any(RouteRequestDTO.class))).thenReturn(routeResponseDTO);

        ResponseEntity<RouteResponseDTO> response = routeController.update(1L, routeRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(routeResponseDTO, response.getBody());
        verify(routeService, times(1)).update(1L, routeRequestDTO);
    }

    @Test
    void testUpdateNotFound() {
        when(routeService.update(anyLong(), any(RouteRequestDTO.class))).thenThrow(new RouteNotFoundException("Route not found with id: 1"));

        Exception exception = assertThrows(RouteNotFoundException.class, () -> {
            routeController.update(1L, routeRequestDTO);
        });

        assertEquals("Route not found with id: 1", exception.getMessage());
        verify(routeService, times(1)).update(1L, routeRequestDTO);
    }

    @Test
    void testDeleteFound() {
        doNothing().when(routeService).delete(anyLong());

        ResponseEntity<Void> response = routeController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(routeService, times(1)).delete(1L);
    }

    @Test
    void testDeleteNotFound() {
        doThrow(new RouteNotFoundException("Route not found with id: 1")).when(routeService).delete(anyLong());

        Exception exception = assertThrows(RouteNotFoundException.class, () -> {
            routeController.delete(1L);
        });

        assertEquals("Route not found with id: 1", exception.getMessage());
        verify(routeService, times(1)).delete(1L);
    }
}
