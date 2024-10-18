package com.csse.api.service;

import com.csse.api.dto.bin.BinRequestDTO;
import com.csse.api.dto.bin.BinResponseDTO;
import com.csse.api.model.*;
import com.csse.api.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BinService {

    private final BinRepository binRepository;

    private final WasteTypeRepository wasteTypeRepository;

    private final ResidentRepository residentRepository;

    private final BinTypesRepository binTypesRepository;

    private final TrackingDeviceRepository trackingDeviceRepository;

    private final ModelMapper modelMapper;

    public BinService(BinRepository binRepository, WasteTypeRepository wasteTypeRepository, ResidentRepository residentRepository, BinTypesRepository binTypesRepository, TrackingDeviceRepository trackingDeviceRepository, ModelMapper modelMapper) {
        this.binRepository = binRepository;
        this.wasteTypeRepository = wasteTypeRepository;
        this.residentRepository = residentRepository;
        this.binTypesRepository = binTypesRepository;
        this.trackingDeviceRepository = trackingDeviceRepository;
        this.modelMapper = modelMapper;
    }

    public BinResponseDTO create(BinRequestDTO dto) {
        Bin bin = new Bin();
        bin.setWasteType(wasteTypeRepository.findById(dto.getWasteTypeId()).orElseThrow());
        bin.setResident(residentRepository.findById(dto.getResidentId()).orElseThrow());
        bin.setBinType(binTypesRepository.findById(dto.getBinTypeId()).orElseThrow());
        // Set tracking device if present
        if (dto.getTrackingDeviceId() != null) {
            // Assuming you have a TrackingDevice repository to find by ID
            bin.setTrackingDevice(trackingDeviceRepository.findById(dto.getTrackingDeviceId()).orElseThrow());
        }
        return modelMapper.map(binRepository.save(bin), BinResponseDTO.class);
    }

    public List<BinResponseDTO> getAll() {
        return binRepository.findAll().stream()
                .map(bin -> modelMapper.map(bin, BinResponseDTO.class))
                .collect(Collectors.toList());
    }

    public BinResponseDTO getById(long id) {
        return binRepository.findById(id)
                .map(bin -> modelMapper.map(bin, BinResponseDTO.class))
                .orElse(null);
    }

    public BinResponseDTO update(long id, BinRequestDTO dto) {
        Bin bin = binRepository.findById(id).orElseThrow();
        bin.setWasteType(wasteTypeRepository.findById(dto.getWasteTypeId()).orElseThrow());
        bin.setResident(residentRepository.findById(dto.getResidentId()).orElseThrow());
        bin.setBinType(binTypesRepository.findById(dto.getBinTypeId()).orElseThrow());
        // Update tracking device if present
        if (dto.getTrackingDeviceId() != null) {
            bin.setTrackingDevice(trackingDeviceRepository.findById(dto.getTrackingDeviceId()).orElseThrow());
        } else {
            bin.setTrackingDevice(null); // Or handle as necessary
        }
        return modelMapper.map(binRepository.save(bin), BinResponseDTO.class);
    }

    public void delete(long id) {
        binRepository.deleteById(id);
    }
}
