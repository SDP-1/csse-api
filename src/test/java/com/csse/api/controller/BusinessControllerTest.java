package com.csse.api.controller;

import com.csse.api.dto.business.BusinessRequestDTO;
import com.csse.api.dto.business.BusinessResponseDTO;
import com.csse.api.exception.BusinessNotFoundException;
import com.csse.api.service.BusinessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BusinessControllerTest {

    @InjectMocks
    private BusinessController businessController;

    @Mock
    private BusinessService businessService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        BusinessRequestDTO requestDTO = new BusinessRequestDTO();
        requestDTO.setBusinessType("Retail");
        requestDTO.setBusinessRegistration("REG123");

        BusinessResponseDTO responseDTO = new BusinessResponseDTO();
        responseDTO.setBusinessType("Retail");
        responseDTO.setBusinessRegistration("REG123");

        when(businessService.create(any(BusinessRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<BusinessResponseDTO> response = businessController.create(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(businessService, times(1)).create(requestDTO);
    }

    @Test
    void testGetAll() {
        BusinessResponseDTO responseDTO = new BusinessResponseDTO();
        responseDTO.setBusinessType("Retail");
        responseDTO.setBusinessRegistration("REG123");

        when(businessService.getAll()).thenReturn(Collections.singletonList(responseDTO));

        ResponseEntity<List<BusinessResponseDTO>> response = businessController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(responseDTO, response.getBody().get(0));
        verify(businessService, times(1)).getAll();
    }

    @Test
    void testGetById() {
        long id = 1L;
        BusinessResponseDTO responseDTO = new BusinessResponseDTO();
        responseDTO.setBusinessType("Retail");
        responseDTO.setBusinessRegistration("REG123");

        when(businessService.getById(id)).thenReturn(responseDTO);

        ResponseEntity<BusinessResponseDTO> response = businessController.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(businessService, times(1)).getById(id);
    }

    @Test
    void testGetById_NotFound() {
        long id = 999L;

        when(businessService.getById(id)).thenThrow(new BusinessNotFoundException("Business not found with id: " + id));

        Exception exception = assertThrows(BusinessNotFoundException.class, () -> businessController.getById(id));

        assertEquals("Business not found with id: " + id, exception.getMessage());
        verify(businessService, times(1)).getById(id);
    }

    @Test
    void testUpdate() {
        long id = 1L;
        BusinessRequestDTO requestDTO = new BusinessRequestDTO();
        requestDTO.setBusinessType("Retail");
        requestDTO.setBusinessRegistration("REG123");

        BusinessResponseDTO responseDTO = new BusinessResponseDTO();
        responseDTO.setBusinessType("Retail");
        responseDTO.setBusinessRegistration("REG123");

        when(businessService.update(eq(id), any(BusinessRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<BusinessResponseDTO> response = businessController.update(id, requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(businessService, times(1)).update(eq(id), any(BusinessRequestDTO.class));
    }

    @Test
    void testDelete() {
        long id = 1L;

        doNothing().when(businessService).delete(id);

        ResponseEntity<Void> response = businessController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(businessService, times(1)).delete(id);
    }
}
