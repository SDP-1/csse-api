package com.csse.api.service;

import com.csse.api.exception.ResourceNotFoundException;
import com.csse.api.model.Bin;
import com.csse.api.repository.BinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BinService {

    @Autowired
    private BinRepository binRepository;

    public List<Bin> findAll() {
        return binRepository.findAll();
    }

    public Optional<Bin> findById(Long id) {
        return binRepository.findById(id);
    }

    public Bin createBin(Bin bin) {
        return binRepository.save(bin);
    }

    public Bin updateBin(Long id, Bin bin) {
        // Check if Bin exists
        if (!binRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bin with id " + id + " not found");
        }

        bin.setId(id); // Set the ID for the update
        return binRepository.save(bin);
    }

    public void deleteBin(Long id) {
        binRepository.deleteById(id);
    }
}
