package com.csse.api.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponseDTO {
    private long id;
    private String name;
    private boolean isSuperAdmin;
}
