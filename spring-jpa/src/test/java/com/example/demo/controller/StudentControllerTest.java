package com.example.demo.controller;

import com.example.demo.api.StudentController;
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;


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


    private List<StudentDTO> getMockedStudents() {
        List<StudentDTO> studentDTOS= new ArrayList<>();

        StudentDTO student = new StudentDTO("John","Doe","email@gmail.com",23);

        studentDTOS.add(student);

        return studentDTOS;
    }


}
