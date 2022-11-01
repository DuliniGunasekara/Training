package com.example.springbootproject.controllers;

import com.example.springbootproject.dto.StudentDTO;
import com.example.springbootproject.dto.mapper.StudentMapper;
import com.example.springbootproject.models.Student;
import com.example.springbootproject.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentservice;

    private final StudentMapper studentMapper;


    public StudentController(StudentService studentservice, StudentMapper studentMapper){
        this.studentservice = studentservice;
        this.studentMapper = studentMapper;
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
    public void createStudent(@RequestBody StudentDTO studentDTO){
        try{
            Student student = studentMapper.mapStudentDtoToStudent(studentDTO);
            studentservice.createStudentService(student);
        }catch (ParseException parseException){

        }


    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable final String studentId){

    }
}
