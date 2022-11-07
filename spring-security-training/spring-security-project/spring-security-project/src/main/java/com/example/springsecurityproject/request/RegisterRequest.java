package com.example.springsecurityproject.request;

import com.example.springsecurityproject.constants.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private List<UserRole> roles;
}
