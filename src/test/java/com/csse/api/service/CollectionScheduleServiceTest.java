package com.csse.api.service;

import com.csse.api.dto.collection_schedule.CollectionScheduleRequestDTO;
import com.csse.api.dto.collection_schedule.CollectionScheduleResponseDTO;
import com.csse.api.exception.CollectionScheduleNotFoundException;
import com.csse.api.model.CollectionSchedule;
import com.csse.api.model.Route;
import com.csse.api.repository.CollectionScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CollectionScheduleServiceTest {

    @InjectMocks
    private CollectionScheduleService collectionScheduleService;

    @Mock
    private CollectionScheduleRepository repository;

    private CollectionSchedule collectionSchedule;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set up a sample CollectionSchedule for testing
        collectionSchedule = new CollectionSchedule();
        collectionSchedule.setId(1L);
        collectionSchedule.setStartDate(new Date());
        collectionSchedule.setEndDate(new Date());
        collectionSchedule.setFrequency(null);
        Route route = new Route();
        route.setRouteId(1L);
        collectionSchedule.setRoute(route);
    }

    @Test
    void create_ShouldReturnResponseDTO() {
        CollectionScheduleRequestDTO requestDTO = new CollectionScheduleRequestDTO();
        requestDTO.setStartDate(new Date());
        requestDTO.setEndDate(new Date());
        requestDTO.setFrequency(null);
        requestDTO.setRouteId(1L);

        when(repository.save(any(CollectionSchedule.class))).thenReturn(collectionSchedule);

        CollectionScheduleResponseDTO responseDTO = collectionScheduleService.create(requestDTO);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getId()).isEqualTo(collectionSchedule.getId());
        verify(repository).save(any(CollectionSchedule.class));
    }

    @Test
    void getAll_ShouldReturnListOfResponseDTOs() {
        when(repository.findAll()).thenReturn(List.of(collectionSchedule));

        List<CollectionScheduleResponseDTO> schedules = collectionScheduleService.getAll();

        assertThat(schedules).isNotEmpty();
        assertThat(schedules.get(0).getId()).isEqualTo(collectionSchedule.getId());
    }

    @Test
    void getById_ShouldReturnResponseDTO_WhenExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(collectionSchedule));

        CollectionScheduleResponseDTO responseDTO = collectionScheduleService.getById(1L);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getId()).isEqualTo(collectionSchedule.getId());
    }

    @Test
    void getById_ShouldThrowException_WhenNotExists() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> collectionScheduleService.getById(999L))
                .isInstanceOf(CollectionScheduleNotFoundException.class)
                .hasMessage("Collection schedule with id 999 not found");
    }

    @Test
    void update_ShouldReturnUpdatedResponseDTO() {
        when(repository.findById(1L)).thenReturn(Optional.of(collectionSchedule));
        when(repository.save(any(CollectionSchedule.class))).thenReturn(collectionSchedule);

        CollectionScheduleRequestDTO requestDTO = new CollectionScheduleRequestDTO();
        requestDTO.setStartDate(new Date(System.currentTimeMillis() + 100000));
        requestDTO.setEndDate(new Date(System.currentTimeMillis() + 200000));
        requestDTO.setFrequency(null);
        requestDTO.setRouteId(1L);

        CollectionScheduleResponseDTO responseDTO = collectionScheduleService.update(1L, requestDTO);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getId()).isEqualTo(collectionSchedule.getId());
        assertThat(collectionSchedule.getStartDate()).isEqualTo(requestDTO.getStartDate());
        assertThat(collectionSchedule.getEndDate()).isEqualTo(requestDTO.getEndDate());
        verify(repository).save(any(CollectionSchedule.class));
    }

    @Test
    void delete_ShouldCallDeleteOnRepository() {
        collectionScheduleService.delete(1L);
        verify(repository).deleteById(1L);
    }
}
