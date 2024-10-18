package com.csse.api.dto;

import com.csse.api.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarbageCollectorRequestDTO {
    private String collectorId;
    private String vehicleRegNo;
    private VehicleType vehicleType;
    private String model;
    private String currentStatus;
    private String currentLocation;
    private long wmaId; // For mapping to the WMA entity
    private List<Long> specialWasteRequestIds; // For mapping to the SpecialWasteRequest entity
}
