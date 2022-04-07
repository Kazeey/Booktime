package com.project.frontMobile.network.response

import java.time.LocalDateTime
import java.util.*

data class BookResponse(
    val id: String,
    val title: String,
    val synopsis: String,
    val category: List<String>,
    val authorsId: List<String>,
    val publicationDate: String,
    val base64: String
    )