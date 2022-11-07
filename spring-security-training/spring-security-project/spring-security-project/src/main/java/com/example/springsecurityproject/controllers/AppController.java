package com.example.springsecurityproject.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AppController {

    private final Logger logger = LoggerFactory.getLogger(AppController.class);

    @GetMapping("/home")
    public String homeController() {
        logger.info("In home controller");
        return "This is home page";
    }

    @GetMapping("/dashboard")
    public String dashboardController() {
        logger.info("In dashboard controller");
        return "This is dashboard page";
    }

    @GetMapping("/manage")
    public String manageController() {
        logger.info("In manage controller");
        return "This is manage page";
    }
}
