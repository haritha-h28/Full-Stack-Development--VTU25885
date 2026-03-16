package com.example.week5.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.week5.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{

    List<Student> findByDepartment(String department);

    List<Student> findByAgeGreaterThan(int age);
}
