package com.project.booktime.controllers;

import com.project.booktime.exception.BookNotFoundException;
import com.project.booktime.model.dto.BookDTO;
import com.project.booktime.model.entity.Book;
import com.project.booktime.services.BookService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> bookDTOList = bookService.findAll();

        return ResponseEntity.ok(bookDTOList);
    }

    @GetMapping("/findBy/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable("id") String id) {
        try {
            BookDTO bookDTO = bookService.findById(id);

            return ResponseEntity.ok().body(bookDTO);
        } catch (BookNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<BookDTO> add(@RequestBody Book book) {
        BookDTO bookDTO = bookService.add(book);

        return ResponseEntity.ok(bookDTO);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") String id, @RequestBody Book book) {
        try {
            BookDTO bookDTO = bookService.update(id, book);

            return ResponseEntity.ok().body(bookDTO);
        } catch (BookNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        try {
            bookService.delete(id);

            return ResponseEntity.noContent().build();
        } catch (BookNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
