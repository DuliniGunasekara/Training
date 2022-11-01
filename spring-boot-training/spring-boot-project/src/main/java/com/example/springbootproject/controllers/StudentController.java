package com.example.springbootproject.controllers;

import com.example.springbootproject.dto.StudentDTO;
import com.example.springbootproject.dto.mapper.StudentMapper;
import com.example.springbootproject.models.Student;
import com.example.springbootproject.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> studentList = studentservice.getAllStudentsService();
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("studentId") final String studentId){
        StudentDTO studentDTO = studentMapper.mapStudentToStudentDTO(studentservice.getStudentService(studentId));
        return new ResponseEntity<>(studentDTO,HttpStatus.OK);
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
