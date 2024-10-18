package com.csse.api.dto;

import com.csse.api.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarbageCollectorDTO {
    private long id; // Assuming the ID of the User base class
    private String collectorId;
    private String vehicleRegNo;
    private VehicleType vehicleType;
    private String model;
    private String currentStatus;
    private String currentLocation;
    private long wmaId; // Assuming WMA ID for mapping
    private List<Long> assignmentIds; // Assuming List of CollectorAssignment IDs for mapping
    private List<Long> specialWasteRequestIds; // Assuming List of SpecialWasteRequest IDs for mapping
}
