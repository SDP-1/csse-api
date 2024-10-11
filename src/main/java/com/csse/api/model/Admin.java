package com.csse.api.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Admin extends User {
    private String name;
    private boolean isSuperAdmin;
}
