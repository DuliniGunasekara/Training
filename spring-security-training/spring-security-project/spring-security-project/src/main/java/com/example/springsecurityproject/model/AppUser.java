package com.example.springsecurityproject.model;

import com.example.springsecurityproject.constants.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "user")
public class AppUser {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;

    private List<UserRole> userRole;

}
