package com.project.booktime.services;

import com.project.booktime.model.dto.BookDTO;
import com.project.booktime.model.entity.Book;
import com.project.booktime.model.entity.User;
import com.project.booktime.model.helper.BookHelper;
import com.project.booktime.model.helper.UserHelper;
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

    public List<BookDTO> findAll() {
        return BookHelper.convertAll(repository.findAll());
    }

    public BookDTO findById(String id) {
        Optional<Book> book = repository.findById(id);

        if (book.isPresent()) {
            return BookHelper.convert(book.get());
        } else {
            // throw ...
            return null;
        }
    }

    public BookDTO add(Book book) {
        return BookHelper.convert(repository.save(book));
    }

    public void delete(Book book) {
        repository.delete(book);
    }
}
