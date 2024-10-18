package com.csse.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialWasteRequestRequestDTO {
    private String title;
    private String description;
    private String status;
    private long wasteTypeId;
    private String location;
    private List<Long> requiredVehicleTypeIds;
    private List<Long> garbageCollectorIds;
}
