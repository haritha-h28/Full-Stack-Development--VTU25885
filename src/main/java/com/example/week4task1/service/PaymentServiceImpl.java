
package com.example.week4task1.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String pay() {
        return "Payment Successful";
    }

}