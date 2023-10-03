package com.example.demo.service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    Optional<StudentDTO> getStudent(Long id);

    void insertStudent(StudentDTO student);
    void updateStudent(Long id, StudentDTO student);
    void deleteStudent(Long id);
}
