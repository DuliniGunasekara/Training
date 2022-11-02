package com.example.springbootproject.controllers;

import com.example.springbootproject.dto.StudentDTO;
import com.example.springbootproject.dto.mapper.StudentMapper;
import com.example.springbootproject.models.Student;
import com.example.springbootproject.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentservice;

    private final StudentMapper studentMapper;


    public StudentController(StudentService studentservice, StudentMapper studentMapper) {
        this.studentservice = studentservice;
        this.studentMapper = studentMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> studentList = studentservice.getAllStudentsService();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("studentId") final String studentId) {
        Student student = studentservice.getStudentService(studentId);
        if (student != null) {
            return new ResponseEntity<>(studentMapper.mapStudentToStudentDTO(student), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        if (!studentDTO.getName().isEmpty() || studentDTO.getName() != null) {
            try {
                StudentDTO newStudentDTO = studentservice.createStudentService(studentMapper
                        .mapStudentDtoToStudent(studentDTO));

                if (newStudentDTO != null) {
                    return new ResponseEntity<>(newStudentDTO, HttpStatus.CREATED);
                }

            } catch (DateTimeParseException dateTimeParseException) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable final String studentId) {
        String deletedStudentId = studentservice.deleteStudentService(studentId);
        if (deletedStudentId != null) {
            return new ResponseEntity<>(deletedStudentId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
