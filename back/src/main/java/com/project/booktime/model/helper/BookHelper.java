package com.project.booktime.model.helper;

import com.project.booktime.model.dto.BookDTO;
import com.project.booktime.model.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookHelper {

    private BookHelper() { }

    public static List<BookDTO> convertAll(List<Book> bookList) {
        List<BookDTO> bookDTOList = new ArrayList<>();

        for (Book book : bookList) {
            bookDTOList.add(convert(book));
        }

        return bookDTOList;
    }

    public static BookDTO convert(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getSynopsis(),
                book.getPublicationDate(),
                book.getCategory(),
                book.getPageCount(),
                book.getRating(),
                book.getAuthorId(),
                book.getBase64()
        );
    }
}
