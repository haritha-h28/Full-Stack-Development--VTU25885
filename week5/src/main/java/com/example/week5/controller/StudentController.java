package com.example.week5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.week5.entity.Student;
import com.example.week5.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id){
        return studentRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id){
        studentRepository.deleteById(id);
    }

    @GetMapping("/department/{dept}")
    public List<Student> getByDepartment(@PathVariable String dept){
        return studentRepository.findByDepartment(dept);
    }

    @GetMapping("/age/{age}")
    public List<Student> getByAge(@PathVariable int age){
        return studentRepository.findByAgeGreaterThan(age);
    }
}