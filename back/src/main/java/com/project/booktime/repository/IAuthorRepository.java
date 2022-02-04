package com.project.booktime.repository;

import com.project.booktime.model.entity.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAuthorRepository extends MongoRepository<Author, String> {
}
