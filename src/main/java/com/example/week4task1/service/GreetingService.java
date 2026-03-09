package com.example.week4task1.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String greet() {
        return "Hello from Service Layer";
    }

}