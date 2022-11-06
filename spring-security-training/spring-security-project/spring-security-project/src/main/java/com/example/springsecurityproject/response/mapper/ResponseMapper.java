package com.example.springsecurityproject.response.mapper;

import com.example.springsecurityproject.Model.AppUser;
import com.example.springsecurityproject.response.RegisterResponse;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

    public RegisterResponse mapAppUserToRegisterResponse(final AppUser appUser){
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(appUser.getId());
        registerResponse.setUsername(appUser.getUsername());
        return registerResponse;
    }
}
