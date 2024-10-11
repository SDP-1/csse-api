package com.csse.api.dto.wma;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WMARequestDTO {
    private String authorityName;
    private String region;
    private String contactNumber;
    private String address;
    private Date lastAuditedDate;
}
