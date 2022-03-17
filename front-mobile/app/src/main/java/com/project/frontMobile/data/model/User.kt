package com.project.frontMobile.data.model

import com.project.frontMobile.R

class User(
    var id: String,
    var pseudo: String,
    var name: String,
    var firstName: String,
    var email: String,
    var library: Library,
    var base64: String,
) {
    override fun toString(): String {
        return "\n{\n" +
                "  id='$id',\n" +
                "  pseudo='$pseudo',\n" +
                "  name='$name',\n" +
                "  firstName='$firstName',\n" +
                "  email='$email',\n" +
                "  library=$library'\n" +
                "}"
    }
}