package com.csse.api.dto.bin_types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinTypesRequestDTO {
    private String name;
    private String capacity;
    private String producer;
    private String type;
}
