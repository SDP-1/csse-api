package com.csse.api.service;

import com.csse.api.exception.ResourceNotFoundException;
import com.csse.api.model.BinTypes;
import com.csse.api.repository.BinTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BinTypesService {

    @Autowired
    private BinTypesRepository binTypesRepository;

    public List<BinTypes> findAll() {
        return binTypesRepository.findAll();
    }

    public Optional<BinTypes> findById(Long id) {
        return binTypesRepository.findById(id);
    }

    public BinTypes createBinType(BinTypes binTypes) {
        return binTypesRepository.save(binTypes);
    }

    public BinTypes updateBinType(Long id, BinTypes binTypes) {
        if (!binTypesRepository.existsById(id)) {
            throw new ResourceNotFoundException("BinType with id " + id + " not found");
        }

        binTypes.setId(id);
        return binTypesRepository.save(binTypes);
    }

    public void deleteBinType(Long id) {
        binTypesRepository.deleteById(id);
    }
}
