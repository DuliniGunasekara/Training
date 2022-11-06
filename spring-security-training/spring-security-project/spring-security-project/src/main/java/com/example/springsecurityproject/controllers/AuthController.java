package com.example.springsecurityproject.controllers;

import com.example.springsecurityproject.request.LoginRequest;
import com.example.springsecurityproject.request.RegisterRequest;
import com.example.springsecurityproject.response.RegisterResponse;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;

    private final UserService userService;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, TokenProvider tokenProvider, UserService userService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwtToken = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("authorization_header","Bearer "+jwtToken);
        return new ResponseEntity<>(jwtToken,httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody final RegisterRequest registerRequest){

        if(!StringUtils.hasLength(registerRequest.getUsername()) && !StringUtils.hasLength(registerRequest.getPassword()) && registerRequest.getRoles().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            RegisterResponse registerResponse = userService.userRegisterService(registerRequest);
            if(registerResponse == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(registerResponse,HttpStatus.CREATED);

        }

    }

}
