package com.example.springbootproject.services;

import com.example.springbootproject.models.Student;
import com.example.springbootproject.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public void createStudentService(final Student student){
        studentRepository.save(student);
    }
}
