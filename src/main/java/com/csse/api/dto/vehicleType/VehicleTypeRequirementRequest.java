package com.csse.api.dto.vehicleType;

import com.csse.api.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleTypeRequirementRequest {
    private VehicleType vehicleType;
    private int quantity;
    private Long specialWasteRequestId; // Assuming this is a foreign key reference
}
