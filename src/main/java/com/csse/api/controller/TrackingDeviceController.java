package com.csse.api.controller;

import com.csse.api.model.TrackingDevice;
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

    // Create a new TrackingDevice
    @PostMapping
    public TrackingDevice createTrackingDevice(@RequestBody TrackingDevice trackingDevice) {
        TrackingDevice createdDevice = trackingDeviceService.createTrackingDevice(trackingDevice);
        databaseReference.child(String.valueOf(createdDevice.getId())).setValueAsync(createdDevice);
        return createdDevice;
    }

    // Read all TrackingDevices
    @GetMapping
    public List<TrackingDevice> getAllTrackingDevices() {
        return trackingDeviceService.getAllTrackingDevices();
    }

    // Read a specific TrackingDevice by ID
    @GetMapping("/{id}")
    public ResponseEntity<TrackingDevice> getTrackingDeviceById(@PathVariable long id) {
        TrackingDevice device = trackingDeviceService.getTrackingDeviceById(id);
        return ResponseEntity.ok(device);
    }

    // Update waste level of a TrackingDevice by ID
    @PostMapping("/updateWasteLevel/{id}")
    public TrackingDevice updateWasteLevel(@PathVariable long id, @RequestParam float wasteLevel) {
        TrackingDevice device = trackingDeviceService.updateWasteLevel(id, wasteLevel);
        databaseReference.child(String.valueOf(id)).setValueAsync(device);
        return device;
    }

    // Update an existing TrackingDevice
    @PutMapping("/{id}")
    public ResponseEntity<TrackingDevice> updateTrackingDevice(@PathVariable long id, @RequestBody TrackingDevice trackingDevice) {
        TrackingDevice updatedDevice = trackingDeviceService.updateTrackingDevice(id, trackingDevice);
        databaseReference.child(String.valueOf(id)).setValueAsync(updatedDevice);
        return ResponseEntity.ok(updatedDevice);
    }

    // Delete a TrackingDevice by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrackingDevice(@PathVariable long id) {
        trackingDeviceService.deleteTrackingDevice(id);
        databaseReference.child(String.valueOf(id)).removeValueAsync();
        return ResponseEntity.noContent().build();
    }
}
