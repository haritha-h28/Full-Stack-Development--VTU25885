package com.example.week4task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.week4task1.component.OptionalComponent;

@RestController
public class OptionalController {

    @Autowired(required = false)
    private OptionalComponent optionalComponent;

    @GetMapping("/optional")
    public String checkOptional() {

        if (optionalComponent != null) {
            return optionalComponent.getMessage();
        } else {
            return "Optional Component Not Available";
        }

    }

}
