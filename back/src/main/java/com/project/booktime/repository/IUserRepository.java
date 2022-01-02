package com.project.booktime.repository;

import com.project.booktime.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
}
