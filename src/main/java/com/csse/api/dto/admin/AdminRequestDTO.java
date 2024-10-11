package com.csse.api.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequestDTO {
    private String name;
    private boolean isSuperAdmin;
}
