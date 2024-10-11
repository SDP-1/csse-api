package com.csse.api.dto.user;

import com.csse.api.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    protected long id;
    protected String email;
    protected UserType userType;
}
