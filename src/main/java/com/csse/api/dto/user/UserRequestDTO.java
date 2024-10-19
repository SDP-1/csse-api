package com.csse.api.dto.user;

import com.csse.api.enums.UserType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class UserRequestDTO {
    private String email;
    private String password;
    private UserType userType;
}
