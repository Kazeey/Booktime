package com.project.booktime.services;

import com.project.booktime.model.entity.Book;
import com.project.booktime.repository.IBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookService {

    private IBookRepository repository;

    public BookService(IBookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Optional<Book> findById(String id) {
        return repository.findById(id);
    }

    public Book add(Book book) {
        return repository.save(book);
    }

    public void delete(Book book) {
        repository.delete(book);
    }
}
