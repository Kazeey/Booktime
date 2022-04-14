package com.project.frontMobile.network.response

data class AuthorResponse(
    var id: String,
    var name: String,
    var firstName: String,
    var booksId: List<String>,
    var base64: String
) {
}