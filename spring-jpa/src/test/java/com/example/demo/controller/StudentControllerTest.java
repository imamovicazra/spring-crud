package com.example.demo.controller;

import com.example.demo.api.StudentController;
import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void getStudents_success() throws Exception {

        when(studentService.getAllStudents()).thenReturn(getMockedStudents());
        mockMvc.perform(get("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getStudent_success() throws Exception {


        when(studentService.getStudent(1L)).thenReturn(getMockedStudent());
        mockMvc.perform(get("/api/v1/student/" + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addStudent_success() throws Exception {
        StudentDTO studentDTO = new StudentDTO("John", "Doe", "john@example.com", 25);

        doNothing().when(studentService).insertStudent(studentDTO);

        mockMvc.perform(post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(studentDTO)))
                .andExpect(status().isOk());
    }
    @Test
    public void updateStudent_success() throws Exception {
        Long studentId = 1L;
        StudentDTO updatedStudentDTO = new StudentDTO("Updated", "Student", "updated@example.com", 30);

        doNothing().when(studentService).updateStudent(studentId, updatedStudentDTO);

        mockMvc.perform(put("/api/v1/student/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedStudentDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStudent_success() throws Exception {
        Long studentId = 1L;

        doNothing().when(studentService).deleteStudent(studentId);

        mockMvc.perform(delete("/api/v1/student/{id}", studentId))
                .andExpect(status().isOk());

    }
    private Optional<StudentDTO> getMockedStudent()
    {
       return Optional.of(new StudentDTO("John", "Doe", "email@gmail.com", 23));
    }

    private List<StudentDTO> getMockedStudents() {
        List<StudentDTO> studentDTOS= new ArrayList<>();

        StudentDTO student = new StudentDTO("John","Doe","email@gmail.com",23);

        studentDTOS.add(student);

        return studentDTOS;
    }

    // Helper method to convert an object to JSON string
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
