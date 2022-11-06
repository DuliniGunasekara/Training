package com.example.springsecurityproject.controllers;

import com.example.springsecurityproject.request.LoginRequest;
import com.example.springsecurityproject.request.RegisterRequest;
import com.example.springsecurityproject.response.LoginResponse;
import com.example.springsecurityproject.response.RegisterResponse;
import com.example.springsecurityproject.response.mapper.ResponseMapper;
import com.example.springsecurityproject.security.jwt.TokenProvider;
import com.example.springsecurityproject.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;

    private final UserService userService;

    private final ResponseMapper responseMapper;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, TokenProvider tokenProvider, UserService userService, ResponseMapper responseMapper) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.responseMapper = responseMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwtToken = tokenProvider.createToken(authentication);
        LoginResponse loginResponse = responseMapper.mapToLoginResponse(jwtToken);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("authorization_header", "Bearer " + jwtToken);
        return new ResponseEntity<>(loginResponse, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody final RegisterRequest registerRequest) {

        if (!StringUtils.hasLength(registerRequest.getUsername()) && !StringUtils.hasLength(registerRequest.getPassword()) && registerRequest.getRoles().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            RegisterResponse registerResponse = userService.userRegisterService(registerRequest);
            if (registerResponse == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);

        }

    }

}
