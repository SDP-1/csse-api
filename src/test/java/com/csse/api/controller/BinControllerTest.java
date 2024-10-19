package com.csse.api.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.csse.api.dto.bin.BinRequestDTO;
import com.csse.api.dto.bin.BinResponseDTO;
import com.csse.api.service.BinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

public class BinControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BinService binService;

    @InjectMocks
    private BinController binController;

    private BinRequestDTO binRequestDTO;
    private BinResponseDTO binResponseDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(binController).build();

        binRequestDTO = new BinRequestDTO();
        // Setup the dto properties here...
        binResponseDTO = new BinResponseDTO();
    }

    @Test
    public void testCreateBin_Success() throws Exception {
        when(binService.create(any(BinRequestDTO.class))).thenReturn(binResponseDTO);

        mockMvc.perform(post("/api/bins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"wasteTypeId\": 1, \"residentId\": 1, \"binTypeId\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        verify(binService, times(1)).create(any(BinRequestDTO.class));
    }

    @Test
    public void testGetAllBins_Success() throws Exception {
        when(binService.getAll()).thenReturn(Collections.singletonList(binResponseDTO));

        mockMvc.perform(get("/api/bins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists());

        verify(binService, times(1)).getAll();
    }

    @Test
    public void testGetById_Success() throws Exception {
        long binId = 1L;
        when(binService.getById(binId)).thenReturn(binResponseDTO);

        mockMvc.perform(get("/api/bins/{id}", binId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        verify(binService, times(1)).getById(binId);
    }

    @Test
    public void testDeleteBin_Success() throws Exception {
        long binId = 1L;
        doNothing().when(binService).delete(binId);

        mockMvc.perform(delete("/api/bins/{id}", binId))
                .andExpect(status().isNoContent());

        verify(binService, times(1)).delete(binId);
    }
}
