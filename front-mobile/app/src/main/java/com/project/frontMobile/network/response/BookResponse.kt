package com.project.frontMobile.network.response

import java.util.*

data class BookResponse(
    val id: String,
    val title: String,
    val synopsis: String,
    val category: String,
    val authorId: String,
    val base64: String
    )