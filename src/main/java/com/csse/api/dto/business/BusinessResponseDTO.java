
package com.csse.api.dto.business;

import com.csse.api.dto.resident.ResidentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessResponseDTO extends ResidentResponseDTO {
    private long id; // Primary key
    private String name; // For mapping to the Resident entity
    private String address; // For mapping to the Resident entity
    private String residentialType; // For mapping to the Resident entity
    private String businessType;
    private String businessRegistration;
    private List<Long> wasteTypeIds; // For mapping to the WasteType entity
}