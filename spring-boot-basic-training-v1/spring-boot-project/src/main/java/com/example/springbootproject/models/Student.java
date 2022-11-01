package com.example.springbootproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Component
@Document(collection = "student")
public class Student {

    @Id
    private String id;
    private String name;
    private LocalDate dob;
    private float average;
}
