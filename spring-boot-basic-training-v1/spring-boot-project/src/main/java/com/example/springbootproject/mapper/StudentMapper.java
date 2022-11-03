package com.example.springbootproject.mapper;

import com.example.springbootproject.Utils.Util;
import com.example.springbootproject.models.Student;
import com.example.springbootproject.request.CreateStudentRequest;
import com.example.springbootproject.response.CreateStudentResponse;
import com.example.springbootproject.response.DeleteStudentResponse;
import com.example.springbootproject.response.GetStudentResponse;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public CreateStudentResponse mapStudentToCreateStudentResponse(final Student student) {
        CreateStudentResponse createStudentResponse = new CreateStudentResponse();
        createStudentResponse.setId(student.getId());
        createStudentResponse.setName(student.getName());
        createStudentResponse.setAverage(student.getAverage());
        createStudentResponse.setDob(student.getDob().toString());
        return createStudentResponse;
    }

    public GetStudentResponse mapStudentToGetStudentResponse(final Student student) {
        GetStudentResponse getStudentResponse = new GetStudentResponse();
        getStudentResponse.setId(student.getId());
        getStudentResponse.setName(student.getName());
        getStudentResponse.setAverage(student.getAverage());
        getStudentResponse.setDob(student.getDob().toString());
        return getStudentResponse;
    }

    public DeleteStudentResponse mapStudentToDeleteStudentResponse(final Student student) {
        DeleteStudentResponse deleteStudentResponse= new DeleteStudentResponse();
        deleteStudentResponse.setId(student.getId());
        deleteStudentResponse.setName(student.getName());
        deleteStudentResponse.setAverage(student.getAverage());
        deleteStudentResponse.setDob(student.getDob().toString());
        return deleteStudentResponse;
    }

    public Student mapCreateStudentRequestToStudent(final CreateStudentRequest createStudentRequest) {
        Student student = new Student();
        student.setName(createStudentRequest.getName());
        student.setAverage(createStudentRequest.getAverage());
        student.setDob(Util.convertStringToDateFormat(createStudentRequest.getDob()));
        return student;
    }


}
