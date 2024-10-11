package com.csse.api.dto.alert_notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertNotificationRequestDTO {
    private String title;
    private String message;
    private String type; // Use String or an enum representation as per your needs
    private Date date;
    private boolean readByUser; // Defaults to false if not provided
    private Long residentId; // Nullable for creating or updating a notification
    private Long wmaId; // Nullable for creating or updating a notification
}
