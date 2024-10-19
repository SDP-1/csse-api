package com.csse.api.dto.admin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequestDTO {
    private String name;
    private boolean isSuperAdmin;
}
