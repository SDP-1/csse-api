package com.csse.api.dto.alert_notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertNotificationResponseDTO {
    private long id; // Primary key
    private String title;
    private String message;
    private String type; // Use String or an enum representation as per your needs
    private Date date;
    private boolean readByUser;
    private Long residentId; // Nullable for associations
    private Long wmaId; // Nullable for associations
}
