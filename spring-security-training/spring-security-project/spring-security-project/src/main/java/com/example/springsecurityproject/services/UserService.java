package com.example.springsecurityproject.services;

import com.example.springsecurityproject.model.AppUser;
import com.example.springsecurityproject.repository.UserRepository;
import com.example.springsecurityproject.request.RegisterRequest;
import com.example.springsecurityproject.request.mapper.RequestMapper;
import com.example.springsecurityproject.response.RegisterResponse;
import com.example.springsecurityproject.response.mapper.ResponseMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    private final RequestMapper requestMapper;

    private final ResponseMapper responseMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("In loadUserByUsername service method");

        AppUser existingUser = userRepository.findByUsername(username).orElse(null);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

        if (existingUser == null) {
            String msg = "Username " + username + " not found!";
            logger.error(msg);
            throw new UsernameNotFoundException(msg);

        } else {
            existingUser.getUserRole().forEach(role -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.toString())));
        }
        return new User(existingUser.getUsername(), existingUser.getPassword(), simpleGrantedAuthorities);

    }

    public RegisterResponse userRegisterService(final RegisterRequest registerRequest) {
        logger.info("In userRegisterService service method");

        AppUser existingUser = userRepository.findByUsername(registerRequest.getUsername()).orElse(null);

        if (existingUser == null) {
            AppUser savedUser = userRepository.save(requestMapper.mapRegisterRequestToAppUser(registerRequest));
            return responseMapper.mapAppUserToRegisterResponse(savedUser);
        }

        return null;
    }
}
