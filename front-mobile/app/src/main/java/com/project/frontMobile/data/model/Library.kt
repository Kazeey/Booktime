package com.project.frontMobile.data.model

import android.util.Log
import com.project.frontMobile.R

class Library(
    val bookList: MutableList<Book>,
    val likedList: MutableList<Book>
) {
    fun isBookAdded(book: Book): Int {
        return when (bookList.any { it.id == book.id }) {
            true -> R.drawable.ic_baseline_remove_24
            else -> R.drawable.ic_baseline_add_24
        }
    }

    fun manageAdded(book: Book) {
        when (bookList.any { it.id == book.id }) {
            true -> bookList.remove(bookList.find { it.id == book.id })
            else -> bookList.add(book)
        }
    }

    fun isBookLiked(book: Book): Int {
        return when (likedList.any { it.id == book.id }) {
            true -> R.drawable.ic_baseline_favorite_24
            else -> R.drawable.ic_baseline_favorite_border_24
        }
    }

    fun manageLiked(book: Book) {
        when (likedList.any { it.id == book.id }) {
            true -> likedList.remove(likedList.find { it.id == book.id })
            else -> likedList.add(book)
        }
    }

    override fun toString(): String {
        return "{\n" +
                "  bookList=$bookList,\n" +
                "  likedList=$likedList\n" +
                "}"
    }
}