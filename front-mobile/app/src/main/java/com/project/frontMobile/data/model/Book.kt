package com.project.frontMobile.data.model

class Book(
    var id: String,
    var title: String,
    val synopsis: String,
    val category: List<String>,
    val authorsId: List<String>,
    val base64: String
    )