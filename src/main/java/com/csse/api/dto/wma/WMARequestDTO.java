package com.csse.api.dto.wma;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WMARequestDTO {
    private String authorityId; // You can remove this from the request if you want to generate it on the server-side
    private String authorityName;
    private String region;
    private String contactNumber;
    private String address;
    private Date lastAuditedDate;
}
