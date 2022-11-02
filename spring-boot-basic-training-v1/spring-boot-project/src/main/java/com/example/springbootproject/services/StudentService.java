package com.example.springbootproject.services;

import com.example.springbootproject.dto.StudentDTO;
import com.example.springbootproject.dto.mapper.StudentMapper;
import com.example.springbootproject.models.Student;
import com.example.springbootproject.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentService(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    public StudentDTO createStudentService(final Student student) {
        Student existingStudent = studentRepository.findByName(student.getName());
        if (existingStudent == null) {
            Student savedStudent = studentRepository.save(student);
            return studentMapper.mapStudentToStudentDTO(savedStudent);
        }
        return null;
    }

    public List<StudentDTO> getAllStudentsService() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().map(studentMapper::mapStudentToStudentDTO).toList();
    }

    public Student getStudentService(final String studentId) {
        Student student = studentRepository.findStudentById(studentId);
        if (student != null) {
            return student;
        }
        return null;
    }

    public String deleteStudentService(String studentId) {
        Student existingStudent = studentRepository.findStudentById(studentId);
        if (existingStudent != null) {
            return studentRepository.deleteStudentById(studentId).getId();
        }
        return null;
    }


}
