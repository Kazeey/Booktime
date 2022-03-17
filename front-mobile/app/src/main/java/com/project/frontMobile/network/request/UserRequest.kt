package com.project.frontMobile.network.request

class UserRequest(
    val id: String,
    val pseudo: String,
    val name: String,
    val firstName: String,
    val email: String,
    val library: List<String>,
    val liked: List<String>,
    val base64: String
) {
}