package com.example.demo.dao;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student,Long> {

}
