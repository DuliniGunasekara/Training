package com.example.springbootproject.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteStudentRequest {

    private String id;
    private String name;
    private String dob;
    private float average;
}
