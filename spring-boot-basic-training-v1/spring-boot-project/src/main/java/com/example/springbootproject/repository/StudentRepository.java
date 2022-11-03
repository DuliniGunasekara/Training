package com.example.springbootproject.repository;

import com.example.springbootproject.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    List<Student> findAll();

    Optional<Student> findStudentById(final String id);

    Optional<Student> deleteStudentById(final String id);

    Optional<Student> findStudentByName(String name);


}
