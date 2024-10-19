package com.csse.api.model;

import com.csse.api.enums.UserType;
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

    public Admin(long id, String email, String password, UserType userType, String name, boolean isSuperAdmin) {
        super(id, email, password, userType); // Call the parent class constructor
        this.name = name;
        this.isSuperAdmin = isSuperAdmin;
    }
}
