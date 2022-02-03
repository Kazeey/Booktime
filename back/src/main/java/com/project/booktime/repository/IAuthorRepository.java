package com.project.booktime.repository;

import com.project.booktime.model.entity.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IAuthorRepository extends MongoRepository<Author, String> {

    @Query("{ 'booksId' : ?0 }")
    List<Author> findAuthorsByBookId(String bookId);
}
