package com.example.springbootproject.services;

import com.example.springbootproject.dto.StudentDTO;
import com.example.springbootproject.dto.mapper.StudentMapper;
import com.example.springbootproject.models.Student;
import com.example.springbootproject.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    private static final Logger logger = Logger.getLogger(StudentService.class.getName());

    public StudentService(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    public StudentDTO createStudentService(final Student student) {
        logger.log(Level.INFO,"In createStudentService method");

        Student existingStudent = studentRepository.findByName(student.getName());
        if (existingStudent == null) {
            Student savedStudent = studentRepository.save(student);
            return studentMapper.mapStudentToStudentDTO(savedStudent);
        }
        return null;
    }

    public List<StudentDTO> getAllStudentsService() {
        logger.log(Level.INFO,"In getAllStudentsService method");

        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().map(studentMapper::mapStudentToStudentDTO).toList();
    }

    public Student getStudentService(final String studentId) {
        logger.log(Level.INFO,"In getStudentService method");

        Student student = studentRepository.findStudentById(studentId);
        if (student != null) {
            return student;
        }
        return null;
    }

    public String deleteStudentService(String studentId) {
        logger.log(Level.INFO,"In deleteStudentService method");

        Student existingStudent = studentRepository.findStudentById(studentId);
        if (existingStudent != null) {
            return studentRepository.deleteStudentById(studentId).getId();
        }
        return null;
    }


}
