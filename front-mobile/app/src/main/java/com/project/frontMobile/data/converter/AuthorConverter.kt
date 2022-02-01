package com.project.frontMobile.data.converter

import com.project.frontMobile.data.model.Author
import com.project.frontMobile.data.model.Book
import com.project.frontMobile.network.response.AuthorResponse
import com.project.frontMobile.network.response.BookResponse

class AuthorConverter {

    fun convert(response: AuthorResponse): Author {
        return Author(
            response.id,
            response.name,
            response.firstName,
            response.base64
        )
    }

    fun convertAll(responses: List<AuthorResponse>): List<Author> {
        val authorList = mutableListOf<Author>()

        for (response: AuthorResponse in responses) {
            authorList.add(convert(response))
        }

        return authorList
    }
}