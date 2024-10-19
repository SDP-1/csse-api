package com.csse.api.dto.resident;

import com.csse.api.dto.user.UserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentRequestDTO extends UserRequestDTO {
    private String name;
    private String address;
    private String residentialType;
    private long wmaId;
    private List<Long> binIds;
    private List<Long> transactionIds;
    private List<Long> notificationIds;
}
