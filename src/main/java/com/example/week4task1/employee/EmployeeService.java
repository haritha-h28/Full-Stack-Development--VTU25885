package com.example.week4task1.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {

    @Autowired
    private Employee employee;

    public String showEmployee() {
        return employee.getEmployeeDetails();
    }
}
