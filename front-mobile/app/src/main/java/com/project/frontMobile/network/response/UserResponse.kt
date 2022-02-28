package com.project.frontMobile.network.response

data class UserResponse(
    val id: String,
    val pseudo: String,
    val name: String,
    val firstName: String,
    val email: String,
    val added: MutableList<String>,
    val liked: MutableList<String>,
    val base64: String
) {
}