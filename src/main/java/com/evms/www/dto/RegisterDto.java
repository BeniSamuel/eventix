package com.evms.www.dto;

import com.evms.www.enums.Role;
import lombok.Getter;

@Getter
public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private int phone;
    private Role role;
    private String location;
}
