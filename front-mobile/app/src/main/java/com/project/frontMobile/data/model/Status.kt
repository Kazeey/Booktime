package com.project.frontMobile.data.model

class Status(
    var statusCode: Int,
    var requestCode: Int
) {
    override fun toString(): String {
        return "Status(statusCode=$statusCode, requestCode=$requestCode)"
    }
}