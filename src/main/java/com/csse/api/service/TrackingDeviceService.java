package com.csse.api.service;

import com.csse.api.exception.ResourceNotFoundException;
import com.csse.api.model.TrackingDevice;
import com.csse.api.repository.TrackingDeviceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrackingDeviceService {

    private final TrackingDeviceRepository trackingDeviceRepository;

    public TrackingDeviceService(TrackingDeviceRepository trackingDeviceRepository) {
        this.trackingDeviceRepository = trackingDeviceRepository;
    }

    public Optional<TrackingDevice> findById(Long id) {
        return trackingDeviceRepository.findById(id);
    }

    public TrackingDevice updateWasteLevel(Long id, float wasteLevel) {
        TrackingDevice device = trackingDeviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TrackingDevice not found"));

        device.setCurrentWasteLevel(wasteLevel);
        device.updateBinStatus();
        return trackingDeviceRepository.save(device);
    }

    public void deleteTrackingDevice(Long id) {
        trackingDeviceRepository.deleteById(id);
    }
}
