package com.example.studentApp.controller;

import com.example.studentApp.responseDTO.StudentResponse;
import com.example.studentApp.Exception.StudentNotFoundException;
import com.example.studentApp.entity.Student;
import com.example.studentApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/addStudent")
    public StudentResponse addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/allStudents")
    public List<StudentResponse> getStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponse getStudent(@PathVariable int id) throws StudentNotFoundException {
        return studentService.getStudent(id);
    }

    @PutMapping("/updateStudent")
    public String updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable int id){
        return studentService.deleteStudent(id);
    }

}
