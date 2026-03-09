package com.example.week4task1.service;

import org.springframework.stereotype.Service;

@Service("smsService")
public class SMSNotificationService implements NotificationService {

    @Override
    public String sendNotification() {
        return "SMS Notification Sent";
    }

}
