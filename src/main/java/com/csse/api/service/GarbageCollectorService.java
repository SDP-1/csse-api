package com.csse.api.service;

import com.csse.api.dto.GarbageCollectorDTO;
import com.csse.api.model.GarbageCollector;
import com.csse.api.repository.GarbageCollectorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GarbageCollectorService {

    @Autowired
    private GarbageCollectorRepository garbageCollectorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GarbageCollectorDTO createGarbageCollector(GarbageCollectorDTO garbageCollectorDTO) {
        GarbageCollector garbageCollector = modelMapper.map(garbageCollectorDTO, GarbageCollector.class);
        garbageCollector = garbageCollectorRepository.save(garbageCollector);
        return modelMapper.map(garbageCollector, GarbageCollectorDTO.class);
    }

    public List<GarbageCollectorDTO> getAllGarbageCollectors() {
        List<GarbageCollector> collectors = garbageCollectorRepository.findAll();
        return collectors.stream()
                .map(collector -> modelMapper.map(collector, GarbageCollectorDTO.class))
                .toList();
    }

    public Optional<GarbageCollectorDTO> getGarbageCollectorById(long id) {
        return garbageCollectorRepository.findById(id)
                .map(collector -> modelMapper.map(collector, GarbageCollectorDTO.class));
    }

    public GarbageCollectorDTO updateGarbageCollector(long id, GarbageCollectorDTO garbageCollectorDTO) {
        GarbageCollector garbageCollector = garbageCollectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garbage Collector not found"));
        modelMapper.map(garbageCollectorDTO, garbageCollector);
        garbageCollector = garbageCollectorRepository.save(garbageCollector);
        return modelMapper.map(garbageCollector, GarbageCollectorDTO.class);
    }

    public void deleteGarbageCollector(long id) {
        garbageCollectorRepository.deleteById(id);
    }
}
