package com.example.week4task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.week4task1.service.NotificationService;

@RestController
public class NotificationController {

    @Autowired
    @Qualifier("emailService")
    private NotificationService notificationService;

    @GetMapping("/notify")
    public String sendNotification() {
        return notificationService.sendNotification();
    }

}
