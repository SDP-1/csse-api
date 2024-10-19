package com.csse.api.dto.wma;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WMARequestDTO {
    private String authorityName;
    private String region;
    private String contactNumber;
    private String address;
    private Date lastAuditedDate;
}
