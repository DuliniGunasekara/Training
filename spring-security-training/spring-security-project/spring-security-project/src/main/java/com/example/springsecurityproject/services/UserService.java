package com.example.springsecurityproject.services;

import com.example.springsecurityproject.Constants.UserRole;
import com.example.springsecurityproject.Model.AppUser;
import com.example.springsecurityproject.Repository.UserRepository;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AppUser existingUser = userRepository.findAppUserByUsername(username).orElse(null);
//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//
//        if(existingUser == null){
//            throw new UsernameNotFoundException("Username "+username+" not found!");
//        }else {
//            existingUser.getUserRole().stream().map(role-> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.name())));
//        }
//        return new User(existingUser.getUsername(), existingUser.getPassword(),simpleGrantedAuthorities);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if (!username.equals("john_doe")) {
            throw new UsernameNotFoundException("Username not found!");
        }

        List<Enum<UserRole>> list = new ArrayList<>();
        list.add(UserRole.USER);
        final AppUser user = new AppUser();
        user.setUsername("john_doe");
        user.setUserRole(list);
        user.setPassword("$2a$12$WGGjDBSXAH8xulWM.HjfE.erMRjp6EJiiAGR203i6AWSnPuJKfLO."); // welCome1/
        user.getUserRole().stream().map(role-> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.name())));

        return new User(user.getUsername(),user.getPassword(),simpleGrantedAuthorities);

    }
}
