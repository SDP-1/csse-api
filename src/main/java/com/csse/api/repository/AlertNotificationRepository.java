package com.csse.api.repository;

import com.csse.api.model.AlertNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertNotificationRepository extends JpaRepository<AlertNotification, Long> {
}
