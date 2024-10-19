package com.csse.api.dto.business;

import com.csse.api.dto.resident.ResidentRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRequestDTO extends ResidentRequestDTO {
    private String name;
    private String address;
    private String residentialType;
    private String businessType;
    private String businessRegistration;
    private List<Long> wasteTypeIds;
}