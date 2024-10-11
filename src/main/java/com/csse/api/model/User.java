package com.csse.api.model;


import com.csse.api.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String email;
    protected String password;
    @Enumerated(EnumType.STRING)
    protected UserType userType;
}
