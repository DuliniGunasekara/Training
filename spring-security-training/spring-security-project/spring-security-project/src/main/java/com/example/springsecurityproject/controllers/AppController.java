package com.example.springsecurityproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AppController {

    @GetMapping("/home")
    public String homeController() {
        return "This is home page";
    }

    @GetMapping("/dashboard")
    public String dashboardController() {
        return "This is dashboard page";
    }

    @GetMapping("/manage")
    public String manageController() {
        return "This is manage page";
    }
}
