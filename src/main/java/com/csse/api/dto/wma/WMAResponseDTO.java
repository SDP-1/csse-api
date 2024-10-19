package com.csse.api.dto.wma;


import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WMAResponseDTO {
    private Long authorityId; // Change to Long type
    private String authorityName;
    private String region;
    private String contactNumber;
    private String address;
    private Date lastAuditedDate;
}
