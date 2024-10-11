package com.csse.api.controller;

import com.csse.api.dto.AlertNotificationDTO;
import com.csse.api.service.AlertNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class AlertNotificationController {

    private final AlertNotificationService alertNotificationService;

    public AlertNotificationController(AlertNotificationService alertNotificationService) {
        this.alertNotificationService = alertNotificationService;
    }

    @PostMapping
    public ResponseEntity<AlertNotificationDTO> createNotification(@RequestBody AlertNotificationDTO alertNotificationDTO) {
        AlertNotificationDTO createdNotification = alertNotificationService.createAlertNotification(alertNotificationDTO);
        return ResponseEntity.ok(createdNotification);
    }

    @GetMapping
    public ResponseEntity<List<AlertNotificationDTO>> getAllNotifications() {
        List<AlertNotificationDTO> notifications = alertNotificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertNotificationDTO> getNotificationById(@PathVariable long id) {
        return alertNotificationService.getNotificationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable long id) {
        alertNotificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}

