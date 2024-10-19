package com.csse.api.dto.admin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDTO {
    private long id;
    private String name;
    private boolean isSuperAdmin;
}
