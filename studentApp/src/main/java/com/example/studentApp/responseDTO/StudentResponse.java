package com.example.studentApp.responseDTO;

import lombok.Data;

@Data
public class StudentResponse {
    private int id;
    private String name;
    private String department;
    private int age;
    private int marks;
}
