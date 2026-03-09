package com.example.week4task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.week4task1.service.GreetingService;

@RestController
public class HelloController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greet")
    public String greet() {
        return greetingService.greet();
    }

}