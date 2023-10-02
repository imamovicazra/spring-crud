package com.example.demo.dto;

import com.example.demo.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentDTO mapToApi(Student student)
    {
        return new StudentDTO(student.getFirstName(),student.getLastName(),student.getEmail(),student.getAge());
    }
}
