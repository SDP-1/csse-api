package com.csse.api.controller;

import com.csse.api.model.TrackingDevice;
import com.csse.api.service.TrackingDeviceService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tracking-device")
public class TrackingDeviceController {

    @Autowired
    private TrackingDeviceService trackingDeviceService;

    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("trackingDevices");

    @PostMapping("/updateWasteLevel/{id}")
    public TrackingDevice updateWasteLevel(@PathVariable long id, @RequestParam float wasteLevel) {
        TrackingDevice device = trackingDeviceService.updateWasteLevel(id, wasteLevel);

        databaseReference.child(String.valueOf(id)).setValueAsync(device);

        return device;
    }


}
