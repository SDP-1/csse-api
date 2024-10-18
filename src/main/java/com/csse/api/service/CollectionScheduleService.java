package com.csse.api.service;

import com.csse.api.dto.CollectionScheduleDTO;
import com.csse.api.model.CollectionSchedule;
import com.csse.api.repository.CollectionScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionScheduleService {

    @Autowired
    private CollectionScheduleRepository collectionScheduleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CollectionScheduleDTO createCollectionSchedule(CollectionScheduleDTO collectionScheduleDTO) {
        CollectionSchedule collectionSchedule = modelMapper.map(collectionScheduleDTO, CollectionSchedule.class);
        collectionSchedule = collectionScheduleRepository.save(collectionSchedule);
        return modelMapper.map(collectionSchedule, CollectionScheduleDTO.class);
    }

    public List<CollectionScheduleDTO> getAllCollectionSchedules() {
        List<CollectionSchedule> schedules = collectionScheduleRepository.findAll();
        return schedules.stream()
                .map(schedule -> modelMapper.map(schedule, CollectionScheduleDTO.class))
                .toList();
    }

    public Optional<CollectionScheduleDTO> getCollectionScheduleById(long id) {
        return collectionScheduleRepository.findById(id)
                .map(schedule -> modelMapper.map(schedule, CollectionScheduleDTO.class));
    }

    public CollectionScheduleDTO updateCollectionSchedule(long id, CollectionScheduleDTO collectionScheduleDTO) {
        CollectionSchedule collectionSchedule = collectionScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Collection Schedule not found"));
        modelMapper.map(collectionScheduleDTO, collectionSchedule);
        collectionSchedule = collectionScheduleRepository.save(collectionSchedule);
        return modelMapper.map(collectionSchedule, CollectionScheduleDTO.class);
    }

    public void deleteCollectionSchedule(long id) {
        collectionScheduleRepository.deleteById(id);
    }
}
