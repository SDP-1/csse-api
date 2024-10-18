package com.csse.api.service;

import com.csse.api.exception.ResourceNotFoundException;
import com.csse.api.model.TrackingDevice;
import com.csse.api.repository.TrackingDeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackingDeviceService {

    private final TrackingDeviceRepository trackingDeviceRepository;

    public TrackingDeviceService(TrackingDeviceRepository trackingDeviceRepository) {
        this.trackingDeviceRepository = trackingDeviceRepository;
    }

    public List<TrackingDevice> getAllTrackingDevices() {
        return trackingDeviceRepository.findAll();
    }

    public TrackingDevice createTrackingDevice(TrackingDevice trackingDevice) {
        return trackingDeviceRepository.save(trackingDevice);
    }

    public TrackingDevice getTrackingDeviceById(Long id) {
        return trackingDeviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TrackingDevice not found"));
    }

    public TrackingDevice updateTrackingDevice(Long id, TrackingDevice trackingDevice) {
        TrackingDevice existingDevice = getTrackingDeviceById(id);

        // Update fields of the existing device
        existingDevice.setName(trackingDevice.getName());
        existingDevice.setType(trackingDevice.getType());
        existingDevice.setCurrentWasteLevel(trackingDevice.getCurrentWasteLevel());
        existingDevice.setStatus(trackingDevice.getStatus());

        return trackingDeviceRepository.save(existingDevice);
    }

    public TrackingDevice updateWasteLevel(Long id, float wasteLevel) {
        TrackingDevice device = getTrackingDeviceById(id);
        device.setCurrentWasteLevel(wasteLevel);
        device.updateBinStatus();
        return trackingDeviceRepository.save(device);
    }

    public void deleteTrackingDevice(Long id) {
        TrackingDevice device = getTrackingDeviceById(id);
        trackingDeviceRepository.delete(device);
    }
}
