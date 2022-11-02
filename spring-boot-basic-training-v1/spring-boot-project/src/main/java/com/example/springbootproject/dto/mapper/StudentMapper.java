package com.example.springbootproject.dto.mapper;

import com.example.springbootproject.Utils.Util;
import com.example.springbootproject.dto.StudentDTO;
import com.example.springbootproject.models.Student;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Component
public class StudentMapper {


    public Student mapStudentDtoToStudent(final StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        if (studentDTO.getDob() != null) {
            student.setDob(Util.convertStringToDateFormat(studentDTO.getDob()));
        } else {
            student.setDob(LocalDate.now());
        }
        student.setAverage(studentDTO.getAverage());
        return student;
    }

    public StudentDTO mapStudentToStudentDTO(final Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setAverage(student.getAverage());
        studentDTO.setDob(student.getDob().toString());
        return studentDTO;
    }
}
