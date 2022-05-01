package com.project.booktime.repository;

import com.project.booktime.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<User, String> {

    @Query("{ 'email': { $eq: ?0 }, 'password': { $eq: ?1} }")
    Optional<User> logIn(String email, String password);
}
