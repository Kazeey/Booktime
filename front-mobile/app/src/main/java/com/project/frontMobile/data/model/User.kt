package com.project.frontMobile.data.model

import com.project.frontMobile.R

class User(
    var id: String,
    var pseudo: String,
    var name: String,
    var firstName: String,
    var email: String,
    var library: MutableList<Book>,
    var liked: MutableList<String>,
    var base64: String,
) {

    fun isBookAdded(bookId: String): Int {
        /*return when (added.contains(bookId)) {
            true -> R.drawable.ic_baseline_remove_24
            else -> R.drawable.ic_baseline_add_24
        }*/
        return R.drawable.ic_baseline_remove_24
    }

    fun manageAdded(bookId: String) {
        /*when (added.contains(bookId)) {
            true -> added.remove(bookId)
            else -> added.add(bookId)
        }*/
    }

    fun isBookLiked(bookId: String): Int {
        return when (liked.contains(bookId)) {
            true -> R.drawable.ic_baseline_favorite_24
            else -> R.drawable.ic_baseline_favorite_border_24
        }
    }

    fun manageLiked(bookId: String) {
        when (liked.contains(bookId)) {
            true -> liked.remove(bookId)
            else -> liked.add(bookId)
        }
    }
}