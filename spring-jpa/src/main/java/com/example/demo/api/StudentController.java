package com.example.demo.api;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1/student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllStudents()
    {

        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id)
    {
        return studentService.getStudent(id)
                .orElse(null);
    }

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody StudentDTO student){
        studentService.insertStudent(student);
        return ResponseEntity.ok("Student added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentDTO student)
    {
        studentService.updateStudent(id,student);
        return ResponseEntity.ok("Student updated");
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id)
    {
        studentService.deleteStudent(id);
    }
}
