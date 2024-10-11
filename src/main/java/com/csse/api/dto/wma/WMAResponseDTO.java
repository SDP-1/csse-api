package com.csse.api.dto.wma;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WMAResponseDTO {
    private String authorityId;
    private String authorityName;
    private String region;
    private String contactNumber;
    private String address;
    private Date lastAuditedDate;
}

