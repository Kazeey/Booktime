package com.project.frontMobile.data.converter

import com.project.frontMobile.data.model.Book
import com.project.frontMobile.network.response.BookResponse

class BookConverter {

    fun convert(response: BookResponse): Book {
        return Book(
            response.id,
            response.title,
            response.synopsis,
            response.category,
            response.authorId,
            response.base64
        )
    }

    fun convertAll(responses: List<BookResponse>): List<Book> {
        val bookList = mutableListOf<Book>()

        for (response: BookResponse in responses) {
            bookList.add(convert(response))
        }

        return bookList
    }
}