package com.example.springbootproject.services;

import com.example.springbootproject.dto.StudentDTO;
import com.example.springbootproject.dto.mapper.StudentMapper;
import com.example.springbootproject.models.Student;
import com.example.springbootproject.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentService(StudentMapper studentMapper, StudentRepository studentRepository){
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    public void createStudentService(final Student student)  {
        studentRepository.save(student);
    }

    public List<Student> getAllStudentsService(){
        List<Student> studentList  = studentRepository.findAll();;
        return studentList;
    }

    public Student getStudentService(final String studentId){
        return studentRepository.findStudentById(studentId);
    }
}
