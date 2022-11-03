package com.example.springbootproject.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentRequest {

    private String name;
    private String dob;
    private float average;

}
