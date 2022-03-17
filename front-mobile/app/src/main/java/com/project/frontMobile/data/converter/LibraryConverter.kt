package com.project.frontMobile.data.converter

import com.project.frontMobile.data.model.Book
import com.project.frontMobile.data.model.Library
import com.project.frontMobile.data.model.User
import com.project.frontMobile.network.response.UserResponse

class LibraryConverter {

    fun initLibrary(user: UserResponse): Library {
        return Library(
            BookConverter().convertAll(user.library),
            BookConverter().convertAll(user.liked)
        )
    }

    fun booksToBooksId(bookList: List<Book>): List<String> {
        val bookIdList = mutableListOf<String>()

        for (book: Book in bookList) {
            bookIdList.add(book.id)
        }

        return bookIdList
    }
}