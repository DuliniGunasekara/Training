package com.example.springbootproject.controllers;

import com.example.springbootproject.request.CreateStudentRequest;
import com.example.springbootproject.response.CreateStudentResponse;
import com.example.springbootproject.response.DeleteStudentResponse;
import com.example.springbootproject.response.GetStudentResponse;
import com.example.springbootproject.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentservice;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);


    public StudentController(StudentService studentservice) {
        this.studentservice = studentservice;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetStudentResponse>> getAllStudents() {
        logger.info("In getAllStudents controller");

        List<GetStudentResponse> studentList = studentservice.getAllStudentsService();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<GetStudentResponse> getStudent(@PathVariable("studentId") final String studentId) {
        logger.info("In getStudent controller");

        GetStudentResponse getStudentResponse = studentservice.getStudentService(studentId);
        if (getStudentResponse != null) {
            return new ResponseEntity<>(getStudentResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<CreateStudentResponse> createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        logger.info("In createStudent controller");

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
        logger.info("In deleteStudent controller");

        DeleteStudentResponse deletedStudentId = studentservice.deleteStudentService(studentId);
        if (deletedStudentId != null) {
            return new ResponseEntity<>(deletedStudentId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
