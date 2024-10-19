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
public class VehicleTypeRequirementResponseDTO {
    private long id;
    private VehicleType vehicleType;
    private int quantity;
    private Long specialWasteRequestId; // Include the special waste request ID in response
}
