package com.project.booktime.repository;

import com.project.booktime.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
