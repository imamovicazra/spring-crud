package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;

    public StudentDTO(@JsonProperty("firstName") String firstName, @JsonProperty("lastName")String lastName,
                      @JsonProperty("email") String email, @JsonProperty("age")Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
