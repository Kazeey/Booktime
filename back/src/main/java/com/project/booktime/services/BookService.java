package com.project.booktime.services;

import com.project.booktime.exception.BookNotFoundException;
import com.project.booktime.model.dto.BookDTO;
import com.project.booktime.model.entity.Book;
import com.project.booktime.model.helper.BookHelper;
import com.project.booktime.repository.IBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookService {

    private final IBookRepository repository;

    public BookService(IBookRepository repository) {
        this.repository = repository;
    }

    public List<BookDTO> findAll() {
        List<Book> bookList = repository.findAll();

        return BookHelper.convertAll(bookList);
    }

    public BookDTO findById(String id) {
        Optional<Book> book = repository.findById(id);

        if (book.isEmpty()) throw new BookNotFoundException();

        return BookHelper.convert(book.get());
    }

    public List<BookDTO> findBookListById(List<String> booksId) {
        Optional<List<Book>> books = repository.findBookListById(booksId);

        if (books.isEmpty()) throw new BookNotFoundException();

        return BookHelper.convertAll(books.get());
    }

    public Boolean findByTitle(String title)
    {
        Book book = repository.findByTitle(title);

        if(book != null)
            return true;
        else
            return false;
    }

    public BookDTO add(Book book) {
        Book createdBook = repository.insert(book);

        return BookHelper.convert(createdBook);
    }

    public BookDTO update(String id, Book book) {
        Optional<Book> optionalBook = repository.findById(id);

        if (optionalBook.isEmpty()) throw new BookNotFoundException();

        Book updatedBook = repository.save(book);

        return BookHelper.convert(updatedBook);
    }

    public void delete(String id) {
        Optional<Book> optionalBook = repository.findById(id);

        if (optionalBook.isEmpty()) throw new BookNotFoundException();

        repository.delete(optionalBook.get());
    }
}
