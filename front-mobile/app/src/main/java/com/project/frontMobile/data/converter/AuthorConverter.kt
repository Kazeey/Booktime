package com.project.frontMobile.data.converter

import com.project.frontMobile.data.model.Author
import com.project.frontMobile.network.response.AuthorResponse

class AuthorConverter {

    fun convert(response: AuthorResponse): Author {
        return Author(
            response.id,
            response.name,
            response.booksId,
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