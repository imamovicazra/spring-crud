package com.example.demo.service;

import com.example.demo.dao.StudentRepository;
import com.example.demo.dto.StudentDTO;
import com.example.demo.dto.StudentMapper;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImplementation implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<StudentDTO> getAllStudents() {
        Iterable<Student> dbStudents=studentRepository.findAll();
        return StreamSupport.stream(dbStudents.spliterator(), false)
                .map(studentMapper::mapToApi)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<StudentDTO> getStudent(Long id) {
        Optional<Student> dbStudentOptional = studentRepository.findById(id);

        return dbStudentOptional.map(studentMapper::mapToApi);
    }

    @Override
    public void insertStudent(StudentDTO student) {
        Student studentToAdd=new Student(student.getFirstName(),student.getLastName(),student.getEmail(),student.getAge());
        studentRepository.save(studentToAdd);
    }

    @Override
    public void updateStudent(Long id, StudentDTO studentDto) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
            // Student with the given ID exists, so we can update it
            Student existingStudent = optionalStudent.get();
            existingStudent.setFirstName(studentDto.getFirstName());
            existingStudent.setLastName(studentDto.getLastName());
            existingStudent.setEmail(studentDto.getEmail());
            existingStudent.setAge(studentDto.getAge());
            studentRepository.save(existingStudent);
        }
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
