
package com.csse.api.dto.resident;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentResponseDTO {
    private long id; // Assuming you have an ID in the User class
    private String name;
    private String address;
    private String residentialType;
    private long wmaId; // For mapping to the WMA entity
    private List<Long> binIds; // For mapping to the Bin entity
    private List<Long> transactionIds; // For mapping to the Transaction entity
    private List<Long> notificationIds; // For mapping to the AlertNotification entity
}