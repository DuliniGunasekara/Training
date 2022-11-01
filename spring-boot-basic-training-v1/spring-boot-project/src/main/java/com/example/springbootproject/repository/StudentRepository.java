package com.example.springbootproject.repository;

import com.example.springbootproject.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {

    List<Student> findAll();

    Student findStudentById(final String id);

    Student deleteStudentById(final String id);


}
