package com.project.frontMobile.network.response

data class UserResponse(
    val id: String,
    val pseudo: String,
    val name: String,
    val firstName: String,
    val email: String,
    val library: MutableList<BookResponse>,
    val liked: MutableList<BookResponse>,
    val base64: String
) {
}