package com.example.studentApp.service;

import com.example.studentApp.responseDTO.StudentResponse;
import com.example.studentApp.Exception.StudentNotFoundException;
import com.example.studentApp.entity.Student;
import com.example.studentApp.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> studentResponseList = students.stream().map(student -> {
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setId(student.getId());
            studentResponse.setName(student.getName());
            studentResponse.setAge(student.getAge());
            studentResponse.setDepartment(student.getDepartment());
            studentResponse.setMarks(student.getMarks());
            return studentResponse;
        }).collect(Collectors.toList());
        return studentResponseList;
    }

    public StudentResponse addStudent(Student student) {
        Student studentdata = studentRepository.save(student);
        StudentResponse studentResponse = modelMapper.map(studentdata, StudentResponse.class);
        return studentResponse;
    }

    public String updateStudent(Student student) {
        Optional<Student> studentData = studentRepository.findById(student.getId());
        if (studentData.isPresent()) {
            Student studentdata = studentRepository.save(student);
            return "Student updated Successfully";
        } else {
            return "Student doesn't exist with id : " + student.getId();
        }
    }

    public String deleteStudent(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return "Student deleted successfully";
        } else {
            return "Student doesn't exist with id: " + id;
        }

    }

    public StudentResponse getStudent(int id) throws StudentNotFoundException {
        Optional<Student> student=studentRepository.findById(id);
        if(studentRepository.findById(id).isPresent()){
            StudentResponse studentResponse=modelMapper.map(student,StudentResponse.class);
            return studentResponse;
        }
        else {
            throw new StudentNotFoundException("Student is not exist with id : "+id);
        }
    }
}
