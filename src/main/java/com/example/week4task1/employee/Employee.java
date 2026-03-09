package com.example.week4task1.employee;

import org.springframework.stereotype.Component;

@Component
public class Employee {

    public String getEmployeeDetails() {
        return "Employee Name: John, Role: Developer";
    }
}
