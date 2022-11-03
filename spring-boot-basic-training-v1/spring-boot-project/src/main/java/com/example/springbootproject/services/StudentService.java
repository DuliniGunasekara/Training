package com.example.springbootproject.services;

import com.example.springbootproject.mapper.StudentMapper;
import com.example.springbootproject.models.Student;
import com.example.springbootproject.repository.StudentRepository;
import com.example.springbootproject.request.CreateStudentRequest;
import com.example.springbootproject.response.CreateStudentResponse;
import com.example.springbootproject.response.DeleteStudentResponse;
import com.example.springbootproject.response.GetStudentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);


    public StudentService(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponse createStudentService(final CreateStudentRequest createStudentRequest) {
        logger.info("In createStudentService method");

        Student existingStudent = studentRepository.findStudentByName(createStudentRequest.getName()).orElse(null);

        if (existingStudent == null) {
            studentMapper.mapCreateStudentRequestToStudent(createStudentRequest);
            Student savedStudent = studentRepository.save(studentMapper.mapCreateStudentRequestToStudent(createStudentRequest));
            return studentMapper.mapStudentToCreateStudentResponse(savedStudent);
        }
        return null;
    }

    public List<GetStudentResponse> getAllStudentsService() {
        logger.info("In getAllStudentsService method");

        List<Student> studentList = studentRepository.findAll();
        if (!studentList.isEmpty()) {
            return studentList.stream().map(studentMapper::mapStudentToGetStudentResponse).toList();
        }

        return new ArrayList<>();
    }

    public GetStudentResponse getStudentService(final String studentId) {
        logger.info("In getStudentService method");

        Student student = studentRepository.findStudentById(studentId).orElse(null);
        if (student != null) {
            return studentMapper.mapStudentToGetStudentResponse(student);
        }
        return null;
    }

    public DeleteStudentResponse deleteStudentService(String studentId) {
        logger.info("In deleteStudentService method");

        Student existingStudent = studentRepository.findStudentById(studentId).orElse(null);
        if (existingStudent != null) {
            studentRepository.deleteStudentById(studentId);
            return studentMapper.mapStudentToDeleteStudentResponse(existingStudent);
        }
        return null;
    }
}
