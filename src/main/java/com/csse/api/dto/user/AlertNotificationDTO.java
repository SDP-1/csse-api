package com.csse.api.dto;

import com.csse.api.enums.NotificatoinType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertNotificationDTO {
    private long id;
    private String title;
    private String message;
    private NotificatoinType type;
    private Date date;
    private boolean readByUser;
    private long residentId;
    private Long wmaId;
}
