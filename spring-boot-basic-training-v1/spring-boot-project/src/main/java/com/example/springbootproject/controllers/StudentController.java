package com.example.springbootproject.controllers;

import com.example.springbootproject.mapper.StudentMapper;
import com.example.springbootproject.request.CreateStudentRequest;
import com.example.springbootproject.response.CreateStudentResponse;
import com.example.springbootproject.response.DeleteStudentResponse;
import com.example.springbootproject.response.GetStudentResponse;
import com.example.springbootproject.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentservice;

    private final StudentMapper studentMapper;

    private static final Logger logger = Logger.getLogger(StudentController.class.getName());


    public StudentController(StudentService studentservice, StudentMapper studentMapper) {
        this.studentservice = studentservice;
        this.studentMapper = studentMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetStudentResponse>> getAllStudents() {
        logger.log(Level.INFO,"In getAllStudents controller");

        List<GetStudentResponse> studentList = studentservice.getAllStudentsService();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<GetStudentResponse> getStudent(@PathVariable("studentId") final String studentId) {
        logger.log(Level.INFO,"In getStudent controller");

        GetStudentResponse getStudentResponse = studentservice.getStudentService(studentId);
        if (getStudentResponse != null) {
            return new ResponseEntity<>(getStudentResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<CreateStudentResponse> createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        logger.log(Level.INFO,"In createStudent controller");

        if (!createStudentRequest.getName().isEmpty() || createStudentRequest.getName() != null) {
            try {
                CreateStudentResponse newStudent = studentservice.createStudentService(createStudentRequest);

                if (newStudent != null) {
                    return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
                }

            } catch (DateTimeParseException dateTimeParseException) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<DeleteStudentResponse> deleteStudent(@PathVariable final String studentId) {
        logger.log(Level.INFO,"In deleteStudent controller");

        DeleteStudentResponse deletedStudentId = studentservice.deleteStudentService(studentId);
        if (deletedStudentId != null) {
            return new ResponseEntity<>(deletedStudentId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
