package com.project.booktime.repository;

import com.project.booktime.model.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IBookRepository extends MongoRepository<Book, String> {
}
