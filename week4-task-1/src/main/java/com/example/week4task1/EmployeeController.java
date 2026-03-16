package com.example.week4task1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.week4task1.employee.EmployeeService;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public String getEmployee() {
        return employeeService.showEmployee();
    }

    @GetMapping("/employee/details")
    public String employeeDetails() {
        return "Employee ID: 101, Name: John, Department: IT";
    }
}