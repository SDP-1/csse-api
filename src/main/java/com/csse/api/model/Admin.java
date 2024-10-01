package com.csse.api.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SystemAdmin {
    private String name;
    private boolean isSuperAdmin;

}
