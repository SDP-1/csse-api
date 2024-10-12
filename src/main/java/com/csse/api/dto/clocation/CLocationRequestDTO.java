package com.csse.api.dto.clocation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CLocationRequestDTO {
    private String address; // Address of the location
    private String description; // Description of the location
}
