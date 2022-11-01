package com.example.springbootproject.controllers;

import com.example.springbootproject.models.Student;
import com.example.springbootproject.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentservice;


    public StudentController(StudentService studentservice){
        this.studentservice = studentservice;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return new ArrayList<>();
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId") final String studentId){
        return new Student();
    }

    @PostMapping
    public void createStudent(@RequestBody Student student){
        studentservice.createStudentService(student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable final String studentId){

    }
}
