package com.csse.api.service;

import com.csse.api.model.WasteType;
import com.csse.api.repository.WasteTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteTypeService {

    private final WasteTypeRepository wasteTypeRepository;

    @Autowired
    public WasteTypeService(WasteTypeRepository wasteTypeRepository) {
        this.wasteTypeRepository = wasteTypeRepository;
    }

    public List<WasteType> findAll() {
        return wasteTypeRepository.findAll();
    }

    public Optional<WasteType> findById(Long id) {
        return wasteTypeRepository.findById(id);
    }

    public WasteType save(WasteType wasteType) {
        return wasteTypeRepository.save(wasteType);
    }

    public void deleteById(Long id) {
        wasteTypeRepository.deleteById(id);
    }
}
