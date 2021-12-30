package com.project.booktime.controllers;

import com.project.booktime.model.entity.Book;
import com.project.booktime.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> findById(@PathVariable("id") String id) {
        return bookService.findById(id);
    }

    @PostMapping()
    public Book add(@RequestBody Book book) {
        return bookService.add(book);
    }

    @DeleteMapping()
    public void delete(@RequestBody Book book) {
        bookService.delete(book);
    }
}
