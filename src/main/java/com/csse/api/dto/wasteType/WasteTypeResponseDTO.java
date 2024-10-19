package com.csse.api.dto.wasteType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WasteTypeResponseDTO {
    private long id;
    private String name;
    private String description;
    private float collectorPricePerKg;
    private float paybackPricePerKg;
    private float sellingPricePerKg;
}
