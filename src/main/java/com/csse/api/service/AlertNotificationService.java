package com.csse.api.service;

import com.csse.api.dto.alert_notification.AlertNotificationRequestDTO;
import com.csse.api.dto.alert_notification.AlertNotificationResponseDTO;
import com.csse.api.model.AlertNotification;
import com.csse.api.repository.AlertNotificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertNotificationService {

    @Autowired
    private AlertNotificationRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public AlertNotificationResponseDTO createAlertNotification(AlertNotificationRequestDTO dto) {
        AlertNotification notification = modelMapper.map(dto, AlertNotification.class);
        AlertNotification savedNotification = repository.save(notification);
        return modelMapper.map(savedNotification, AlertNotificationResponseDTO.class);
    }

    public List<AlertNotificationResponseDTO> getAllNotifications() {
        return repository.findAll().stream()
                .map(notification -> modelMapper.map(notification, AlertNotificationResponseDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<AlertNotificationResponseDTO> getNotificationById(long id) {
        return repository.findById(id)
                .map(notification -> modelMapper.map(notification, AlertNotificationResponseDTO.class));
    }

    public AlertNotificationResponseDTO updateNotification(long id, AlertNotificationRequestDTO dto) {
        AlertNotification existingNotification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        modelMapper.map(dto, existingNotification); // Update fields
        AlertNotification updatedNotification = repository.save(existingNotification);
        return modelMapper.map(updatedNotification, AlertNotificationResponseDTO.class);
    }

    public void deleteNotification(long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Notification not found");
        }
        repository.deleteById(id);
    }
}
