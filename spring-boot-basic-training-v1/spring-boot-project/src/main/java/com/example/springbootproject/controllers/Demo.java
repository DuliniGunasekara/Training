package com.example.springbootproject.controllers;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@NoArgsConstructor
public class Demo {

    @GetMapping
    public String helloMethod() {
        return "Hello Spring Boot!";
    }
}
