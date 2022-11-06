package com.example.springsecurityproject.request.mapper;

import com.example.springsecurityproject.Model.AppUser;
import com.example.springsecurityproject.request.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RequestMapper {

    private PasswordEncoder passwordEncoder;

    public AppUser mapRegisterRequestToAppUser(final RegisterRequest registerRequest) {
        AppUser appUser = new AppUser();
        appUser.setUsername(registerRequest.getUsername());
        appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        appUser.setUserRole(registerRequest.getRoles().stream().toList());

        return appUser;
    }
}
