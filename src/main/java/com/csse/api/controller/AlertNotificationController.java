package com.csse.api.controller;

import com.csse.api.dto.alert_notification.AlertNotificationRequestDTO;
import com.csse.api.dto.alert_notification.AlertNotificationResponseDTO;
import com.csse.api.service.AlertNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class AlertNotificationController {

    private final AlertNotificationService alertNotificationService;

    public AlertNotificationController(AlertNotificationService alertNotificationService) {
        this.alertNotificationService = alertNotificationService;
    }

    @PostMapping
    public ResponseEntity<AlertNotificationResponseDTO> createNotification(@RequestBody AlertNotificationRequestDTO requestDTO) {
        AlertNotificationResponseDTO createdNotification = alertNotificationService.createAlertNotification(requestDTO);
        return ResponseEntity.ok(createdNotification);
    }

    @GetMapping
    public ResponseEntity<List<AlertNotificationResponseDTO>> getAllNotifications() {
        List<AlertNotificationResponseDTO> notifications = alertNotificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertNotificationResponseDTO> getNotificationById(@PathVariable long id) {
        Optional<AlertNotificationResponseDTO> notification = alertNotificationService.getNotificationById(id);
        return notification.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertNotificationResponseDTO> updateNotification(@PathVariable long id, @RequestBody AlertNotificationRequestDTO requestDTO) {
        AlertNotificationResponseDTO updatedNotification = alertNotificationService.updateNotification(id, requestDTO);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable long id) {
        alertNotificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
