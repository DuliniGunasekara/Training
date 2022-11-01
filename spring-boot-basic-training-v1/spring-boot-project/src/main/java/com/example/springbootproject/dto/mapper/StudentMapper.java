package com.example.springbootproject.dto.mapper;

import com.example.springbootproject.Utils.Util;
import com.example.springbootproject.dto.StudentDTO;
import com.example.springbootproject.models.Student;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Component
public class StudentMapper {


    public Student mapStudentDtoToStudent(final StudentDTO studentDTO) throws ParseException {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setDob(Util.convertStringToDateFormat(studentDTO.getDob()));
        student.setAverage(studentDTO.getAverage());
        return student;
    }

    public StudentDTO mapStudentToStudentDTO(final Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setAverage(student.getAverage());
        studentDTO.setDob(Util.convertDateToString(student.getDob()));
        return studentDTO;
    }
}
