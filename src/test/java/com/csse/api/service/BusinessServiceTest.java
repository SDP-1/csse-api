package com.csse.api.service;

import com.csse.api.dto.business.BusinessRequestDTO;
import com.csse.api.dto.business.BusinessResponseDTO;
import com.csse.api.exception.BusinessNotFoundException;
import com.csse.api.model.Business;
import com.csse.api.model.WasteType;
import com.csse.api.repository.BusinessRepository;
import com.csse.api.repository.WasteTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BusinessServiceTest {

    @InjectMocks
    private BusinessService businessService;

    @Mock
    private BusinessRepository businessRepository;

    @Mock
    private WasteTypeRepository wasteTypeRepository;

    private Business business;
    private WasteType wasteType;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        business = new Business();
        business.setId(1L);
        business.setName("Test Business");
        business.setAddress("123 Test St");
        business.setResidentialType("Commercial");
        business.setBusinessType("Retail");
        business.setBusinessRegistration("123456789");

        wasteType = new WasteType();
        wasteType.setId(1L);
        wasteType.setName("Plastic");
    }

    @Test
    public void createBusiness() {
        wasteType = new WasteType();
        wasteType.setId(1L);
        wasteType.setName("Plastic");

        when(wasteTypeRepository.findAllById(any())).thenReturn(Arrays.asList(wasteType));
        when(businessRepository.save(any(Business.class))).thenReturn(business);

        BusinessRequestDTO requestDTO = new BusinessRequestDTO("Test Business", "123 Test St", "Commercial", "Retail", "123456789", Arrays.asList(1L));

        BusinessResponseDTO responseDTO = businessService.create(requestDTO);

        assertEquals(business.getName(), responseDTO.getName());
        assertEquals(business.getAddress(), responseDTO.getAddress());
    }

    @Test
    public void getAllBusinesses() {
        when(businessRepository.findAll()).thenReturn(Arrays.asList(business));

        List<BusinessResponseDTO> responseDTOs = businessService.getAll();

        assertEquals(1, responseDTOs.size());
        assertEquals(business.getName(), responseDTOs.get(0).getName());
    }

    @Test
    public void getBusinessById() {
        when(businessRepository.findById(1L)).thenReturn(Optional.of(business));

        BusinessResponseDTO responseDTO = businessService.getById(1L);

        assertEquals(business.getName(), responseDTO.getName());
    }

    @Test
    public void getBusinessById_NotFound() {
        when(businessRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BusinessNotFoundException.class, () -> businessService.getById(1L));
    }

    @Test
    public void updateBusiness() {
        when(businessRepository.findById(1L)).thenReturn(Optional.of(business));
        when(businessRepository.save(any(Business.class))).thenReturn(business);

        BusinessRequestDTO requestDTO = new BusinessRequestDTO("Updated Business", "123 Test St", "Commercial", "Retail", "987654321", Arrays.asList(1L));

        BusinessResponseDTO responseDTO = businessService.update(1L, requestDTO);

        assertEquals("Updated Business", responseDTO.getName());
    }

    @Test
    public void updateBusiness_NotFound() {
        when(businessRepository.findById(1L)).thenReturn(Optional.empty());

        BusinessRequestDTO requestDTO = new BusinessRequestDTO("Updated Business", "123 Test St", "Commercial", "Retail", "987654321", Arrays.asList(1L));

        assertThrows(BusinessNotFoundException.class, () -> businessService.update(1L, requestDTO));
    }

    @Test
    public void deleteBusiness() {
        doNothing().when(businessRepository).deleteById(1L);
        businessService.delete(1L);
        verify(businessRepository, times(1)).deleteById(1L);
    }
}
