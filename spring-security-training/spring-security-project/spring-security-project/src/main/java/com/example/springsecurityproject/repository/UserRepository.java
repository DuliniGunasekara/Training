package com.example.springsecurityproject.repository;

import com.example.springsecurityproject.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {
    Optional<AppUser> findByUsername(final String username);
}
