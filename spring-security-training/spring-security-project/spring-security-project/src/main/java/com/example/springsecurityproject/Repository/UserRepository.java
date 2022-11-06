package com.example.springsecurityproject.Repository;

import com.example.springsecurityproject.Model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {
    Optional<AppUser> findAppUserByUsername(final String username);
}
