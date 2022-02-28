package com.project.frontMobile.network.response

data class BookResponse(
    val id: String,
    val title: String,
    val synopsis: String,
    val category: List<String>,
    val authorsId: List<String>,
    val base64: String
    )