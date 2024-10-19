package com.csse.api.service;

import com.csse.api.dto.bin.BinRequestDTO;
import com.csse.api.dto.bin.BinResponseDTO;
import com.csse.api.exception.BinNotFoundException;
import com.csse.api.model.*;
import com.csse.api.repository.*;
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

    @Autowired
    public BinService(BinRepository binRepository, WasteTypeRepository wasteTypeRepository,
                      ResidentRepository residentRepository, BinTypesRepository binTypesRepository,
                      TrackingDeviceRepository trackingDeviceRepository) {
        this.binRepository = binRepository;
        this.wasteTypeRepository = wasteTypeRepository;
        this.residentRepository = residentRepository;
        this.binTypesRepository = binTypesRepository;
        this.trackingDeviceRepository = trackingDeviceRepository;
    }

    public BinResponseDTO create(BinRequestDTO dto) {
        Bin bin = new Bin();
        bin.setWasteType(wasteTypeRepository.findById(dto.getWasteTypeId())
                .orElseThrow(() -> new BinNotFoundException("WasteType not found")));
        bin.setResident(residentRepository.findById(dto.getResidentId())
                .orElseThrow(() -> new BinNotFoundException("Resident not found")));
        bin.setBinType(binTypesRepository.findById(dto.getBinTypeId())
                .orElseThrow(() -> new BinNotFoundException("BinType not found")));

        if (dto.getTrackingDeviceId() != null) {
            bin.setTrackingDevice(trackingDeviceRepository.findById(dto.getTrackingDeviceId())
                    .orElseThrow(() -> new BinNotFoundException("TrackingDevice not found")));
        }

        Bin savedBin = binRepository.save(bin);
        return convertToResponseDTO(savedBin);
    }

    public List<BinResponseDTO> getAll() {
        return binRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public BinResponseDTO getById(long id) {
        Bin bin = binRepository.findById(id)
                .orElseThrow(() -> new BinNotFoundException("Bin not found"));
        return convertToResponseDTO(bin);
    }

    public BinResponseDTO update(long id, BinRequestDTO dto) {
        Bin bin = binRepository.findById(id)
                .orElseThrow(() -> new BinNotFoundException("Bin not found"));

        bin.setWasteType(wasteTypeRepository.findById(dto.getWasteTypeId())
                .orElseThrow(() -> new BinNotFoundException("WasteType not found")));
        bin.setResident(residentRepository.findById(dto.getResidentId())
                .orElseThrow(() -> new BinNotFoundException("Resident not found")));
        bin.setBinType(binTypesRepository.findById(dto.getBinTypeId())
                .orElseThrow(() -> new BinNotFoundException("BinType not found")));

        if (dto.getTrackingDeviceId() != null) {
            bin.setTrackingDevice(trackingDeviceRepository.findById(dto.getTrackingDeviceId())
                    .orElseThrow(() -> new BinNotFoundException("TrackingDevice not found")));
        } else {
            bin.setTrackingDevice(null);
        }

        Bin updatedBin = binRepository.save(bin);
        return convertToResponseDTO(updatedBin);
    }

    public void delete(long id) {
        binRepository.deleteById(id);
    }

    private BinResponseDTO convertToResponseDTO(Bin bin) {
        BinResponseDTO dto = new BinResponseDTO();
        dto.setId(bin.getId());
        dto.setWasteTypeId(bin.getWasteType().getId());
        dto.setResidentId(bin.getResident() != null ? bin.getResident().getId() : null);
        dto.setBinTypeId(bin.getBinType().getId());
        dto.setTrackingDeviceId(bin.getTrackingDevice() != null ? bin.getTrackingDevice().getId() : null);
        return dto;
    }
}
