package com.csse.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentDTO {
    private long id;
    private String name;
    private String address;
    private String residentialType;
    private Long wmaId;
    private List<Long> binIds;
    private List<Long> transactionIds;
    private List<Long> notificationIds;
}
