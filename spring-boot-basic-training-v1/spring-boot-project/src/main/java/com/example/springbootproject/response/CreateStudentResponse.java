package com.example.springbootproject.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentResponse {
    private String id;
    private String name;
    private String dob;
    private float average;
}
