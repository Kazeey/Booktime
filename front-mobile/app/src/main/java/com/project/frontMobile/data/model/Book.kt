package com.project.frontMobile.data.model

import java.util.*

class Book(
    var id: String,
    var title: String,
    val synopsis: String,
    val category: String,
    val authorId: String,
    val base64: String
    )