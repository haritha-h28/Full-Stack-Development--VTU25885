
package com.example.week4task1.service;

import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailNotificationService implements NotificationService {

    @Override
    public String sendNotification() {
        return "Email Notification Sent";
    }

}