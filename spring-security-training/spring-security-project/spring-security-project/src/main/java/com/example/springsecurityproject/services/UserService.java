package com.example.springsecurityproject.services;

import com.example.springsecurityproject.Model.AppUser;
import com.example.springsecurityproject.Repository.UserRepository;
import com.example.springsecurityproject.request.RegisterRequest;
import com.example.springsecurityproject.request.mapper.RequestMapper;
import com.example.springsecurityproject.response.RegisterResponse;
import com.example.springsecurityproject.response.mapper.ResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RequestMapper requestMapper;

    private final ResponseMapper responseMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser existingUser = userRepository.findAppUserByUsername(username).orElse(null);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

        if (existingUser == null) {
            throw new UsernameNotFoundException("Username " + username + " not found!");

        } else {
            existingUser.getUserRole().forEach(role -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.toString())));
        }
        return new User(existingUser.getUsername(), existingUser.getPassword(), simpleGrantedAuthorities);


    }

    public RegisterResponse userRegisterService(final RegisterRequest registerRequest) {

        AppUser existingUser = userRepository.findAppUserByUsername(registerRequest.getUsername()).orElse(null);

        if (existingUser == null) {
            AppUser savedUser = userRepository.save(requestMapper.mapRegisterRequestToAppUser(registerRequest));
            return responseMapper.mapAppUserToRegisterResponse(savedUser);
        }

        return null;

    }
}
