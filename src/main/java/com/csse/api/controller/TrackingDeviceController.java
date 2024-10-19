package com.csse.api.controller;

import com.csse.api.dto.tracking_device.TrackingDeviceRequestDTO;
import com.csse.api.dto.tracking_device.TrackingDeviceResponseDTO;
import com.csse.api.service.TrackingDeviceService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking-device")
public class TrackingDeviceController {

    @Autowired
    private TrackingDeviceService trackingDeviceService;

    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("trackingDevices");

    @PostMapping
    public TrackingDeviceResponseDTO createTrackingDevice(@RequestBody TrackingDeviceRequestDTO trackingDeviceRequestDTO) {
        TrackingDeviceResponseDTO createdDevice = trackingDeviceService.createTrackingDevice(trackingDeviceRequestDTO);
        databaseReference.child(String.valueOf(createdDevice.getId())).setValueAsync(createdDevice);
        return createdDevice;
    }

    @GetMapping
    public List<TrackingDeviceResponseDTO> getAllTrackingDevices() {
        return trackingDeviceService.getAllTrackingDevices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackingDeviceResponseDTO> getTrackingDeviceById(@PathVariable long id) {
        TrackingDeviceResponseDTO device = trackingDeviceService.getTrackingDeviceById(id);
        return ResponseEntity.ok(device);
    }

    @PostMapping("/updateWasteLevel/{id}")
    public TrackingDeviceResponseDTO updateWasteLevel(@PathVariable long id, @RequestParam float wasteLevel) {
        TrackingDeviceResponseDTO device = trackingDeviceService.updateWasteLevel(id, wasteLevel);
        databaseReference.child(String.valueOf(id)).setValueAsync(device);
        return device;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackingDeviceResponseDTO> updateTrackingDevice(@PathVariable long id, @RequestBody TrackingDeviceRequestDTO trackingDeviceRequestDTO) {
        TrackingDeviceResponseDTO updatedDevice = trackingDeviceService.updateTrackingDevice(id, trackingDeviceRequestDTO);
        databaseReference.child(String.valueOf(id)).setValueAsync(updatedDevice);
        return ResponseEntity.ok(updatedDevice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrackingDevice(@PathVariable long id) {
        trackingDeviceService.deleteTrackingDevice(id);
        databaseReference.child(String.valueOf(id)).removeValueAsync();
        return ResponseEntity.noContent().build();
    }
}
