package com.csse.api.service;

import com.csse.api.dto.AlertNotificationDTO;
import com.csse.api.model.AlertNotification;
import com.csse.api.repository.AlertNotificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertNotificationService {

    @Autowired
    private AlertNotificationRepository alertNotificationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AlertNotificationDTO createAlertNotification(AlertNotificationDTO alertNotificationDTO) {
        AlertNotification alertNotification = modelMapper.map(alertNotificationDTO, AlertNotification.class);
        alertNotification = alertNotificationRepository.save(alertNotification);
        return modelMapper.map(alertNotification, AlertNotificationDTO.class);
    }

    public List<AlertNotificationDTO> getAllNotifications() {
        List<AlertNotification> notifications = alertNotificationRepository.findAll();
        return notifications.stream()
                .map(notification -> modelMapper.map(notification, AlertNotificationDTO.class))
                .toList();
    }

    public Optional<AlertNotificationDTO> getNotificationById(long id) {
        return alertNotificationRepository.findById(id)
                .map(notification -> modelMapper.map(notification, AlertNotificationDTO.class));
    }

    public void deleteNotification(long id) {
        alertNotificationRepository.deleteById(id);
    }
}
