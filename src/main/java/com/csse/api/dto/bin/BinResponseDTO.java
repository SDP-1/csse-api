package com.csse.api.dto.bin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinResponseDTO {
    private long id; // Primary key
    private long wasteTypeId; // For mapping to the WasteType entity
    private long residentId; // For mapping to the Resident entity
    private long binTypeId; // For mapping to the BinTypes entity
    private Long trackingDeviceId; // Nullable for tracking device
}
