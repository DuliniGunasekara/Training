package com.example.springsecurityproject.security;

import com.example.springsecurityproject.security.jwt.JwtConfigurer;
import com.example.springsecurityproject.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig  {

    private final TokenProvider tokenProvider;

    protected SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private JwtConfigurer jwtSecurityConfigurerAdapter(){
        return new JwtConfigurer(tokenProvider);
    }

   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth->{
                    auth.antMatchers("/user/**").permitAll();
                    auth.antMatchers("/home").permitAll();
                    auth.antMatchers("/dashboard").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
                    auth.antMatchers("/manage").hasAnyAuthority("ROLE_ADMIN");
                })
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .apply(jwtSecurityConfigurerAdapter());

         return http.build();

    }


}
