package com.project.frontMobile.data.model

class User(
    var id: String,
    var pseudo: String,
    var name: String,
    var firstName: String,
    var email: String,
    var library: MutableList<String>,
    var liked: MutableList<String>,
    var base64: String,
) {
    override fun toString(): String {
        return "User(id='$id', pseudo='$pseudo', name='$name', firstName='$firstName', email='$email', library=$library, liked=$liked)"
    }
}