package com.example.springbootproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Component
@Document(collection = "student")
public class Student {

    @Id
    private String id;
    private String name;
    private Date dob;
    private float average;
}
