package com.project.frontMobile.data.model

import java.io.Serializable

class Author(
    var id: String,
    var name: String,
    var firstName: String,
    var booksId: List<String>,
    var base64: String
): Serializable