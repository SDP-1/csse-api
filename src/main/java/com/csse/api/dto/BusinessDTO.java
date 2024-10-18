package com.csse.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDTO extends ResidentDTO {
    private String businessType;
    private String businessRegistration;
    private List<Long> wasteTypeIds;
}
