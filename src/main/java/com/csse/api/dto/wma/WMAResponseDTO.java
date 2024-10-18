package com.csse.api.dto.wma;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WMAResponseDTO {
    private Long authorityId; // Change to Long type
    private String authorityName;
    private String region;
    private String contactNumber;
    private String address;
    private Date lastAuditedDate;
}
