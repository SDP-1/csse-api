package com.csse.api.service;

import com.csse.api.dto.tracking_device.TrackingDeviceRequestDTO;
import com.csse.api.dto.tracking_device.TrackingDeviceResponseDTO;
import com.csse.api.exception.ResourceNotFoundException;
import com.csse.api.exception.TrackingDeviceException;
import com.csse.api.model.TrackingDevice;
import com.csse.api.repository.TrackingDeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackingDeviceService {

    private final TrackingDeviceRepository trackingDeviceRepository;

    public TrackingDeviceService(TrackingDeviceRepository trackingDeviceRepository) {
        this.trackingDeviceRepository = trackingDeviceRepository;
    }

    public List<TrackingDeviceResponseDTO> getAllTrackingDevices() {
        return trackingDeviceRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public TrackingDeviceResponseDTO createTrackingDevice(TrackingDeviceRequestDTO trackingDeviceRequestDTO) {
        if (trackingDeviceRequestDTO.getCurrentWasteLevel() < 0) {
            throw new TrackingDeviceException("Current waste level cannot be negative.");
        }

        TrackingDevice trackingDevice = convertToEntity(trackingDeviceRequestDTO);
        return convertToResponseDTO(trackingDeviceRepository.save(trackingDevice));
    }

    public TrackingDeviceResponseDTO getTrackingDeviceById(Long id) {
        return convertToResponseDTO(getTrackingDeviceEntityById(id));
    }

    public TrackingDeviceResponseDTO updateTrackingDevice(Long id, TrackingDeviceRequestDTO trackingDeviceRequestDTO) {
        TrackingDevice existingDevice = getTrackingDeviceEntityById(id);

        if (trackingDeviceRequestDTO.getCurrentWasteLevel() < 0) {
            throw new TrackingDeviceException("Current waste level cannot be negative.");
        }

        existingDevice.setName(trackingDeviceRequestDTO.getName());
        existingDevice.setType(trackingDeviceRequestDTO.getType());
        existingDevice.setCurrentWasteLevel(trackingDeviceRequestDTO.getCurrentWasteLevel());
        existingDevice.setStatus(trackingDeviceRequestDTO.getStatus());

        // Update bin status based on the new waste level
        existingDevice.updateBinStatus();

        return convertToResponseDTO(trackingDeviceRepository.save(existingDevice));
    }

    public TrackingDeviceResponseDTO updateWasteLevel(Long id, float wasteLevel) {
        if (wasteLevel < 0) {
            throw new TrackingDeviceException("Waste level cannot be negative.");
        }

        TrackingDevice device = getTrackingDeviceEntityById(id);
        device.setCurrentWasteLevel(wasteLevel);
        device.updateBinStatus(); // Update bin status based on waste level
        return convertToResponseDTO(trackingDeviceRepository.save(device));
    }

    public void deleteTrackingDevice(Long id) {
        TrackingDevice device = getTrackingDeviceEntityById(id);
        trackingDeviceRepository.delete(device);
    }

    private TrackingDevice getTrackingDeviceEntityById(Long id) {
        return trackingDeviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TrackingDevice not found"));
    }

    private TrackingDeviceResponseDTO convertToResponseDTO(TrackingDevice trackingDevice) {
        TrackingDeviceResponseDTO dto = new TrackingDeviceResponseDTO();
        dto.setId(trackingDevice.getId());
        dto.setName(trackingDevice.getName());
        dto.setType(trackingDevice.getType());
        dto.setCurrentWasteLevel(trackingDevice.getCurrentWasteLevel());
        dto.setStatus(trackingDevice.getStatus());
        dto.setBinStatus(trackingDevice.getBinStatus()); // Ensure binStatus is set
        return dto;
    }

    private TrackingDevice convertToEntity(TrackingDeviceRequestDTO dto) {
        TrackingDevice trackingDevice = new TrackingDevice();
        trackingDevice.setName(dto.getName());
        trackingDevice.setType(dto.getType());
        trackingDevice.setCurrentWasteLevel(dto.getCurrentWasteLevel());
        trackingDevice.setStatus(dto.getStatus());
        trackingDevice.setBinStatus(dto.getBinStatus());
        return trackingDevice;
    }
}
