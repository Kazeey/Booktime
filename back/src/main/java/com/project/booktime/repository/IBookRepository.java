package com.project.booktime.repository;

import com.project.booktime.model.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IBookRepository extends MongoRepository<Book, String>
{
    Book findByISBN(String ISBN);
    Book findByTitle(String title);

    @Query("{ _id: { $in: ?0 } }")
    Optional<List<Book>> findBookListById(List<String> booksId);
}
