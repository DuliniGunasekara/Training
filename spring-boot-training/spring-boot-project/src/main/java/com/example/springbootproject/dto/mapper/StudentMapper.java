package com.example.springbootproject.dto.mapper;

import com.example.springbootproject.dto.StudentDTO;
import com.example.springbootproject.models.Student;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class StudentMapper {

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public Student mapStudentDtoToStudent(final StudentDTO studentDTO) throws ParseException {
        Date dob = dateFormatter.parse(studentDTO.getDob());
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setDob(dob);
        student.setAverage(studentDTO.getAverage());
        return student;
    }

    public StudentDTO mapStudentToStudentDTO(final Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setAverage(student.getAverage());
        studentDTO.setDob(student.getDob().toString());
        return studentDTO;
    }
}
