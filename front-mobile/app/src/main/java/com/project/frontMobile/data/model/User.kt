package com.project.frontMobile.data.model

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import com.project.frontMobile.R

class User(
    var id: String,
    var pseudo: String,
    var name: String,
    var firstName: String,
    var email: String,
    var birthday: String,
    var added: MutableList<String>,
    var liked: MutableList<String>,
    var base64: String,
) {

    fun isBookAdded(bookId: String): Int {
        return when (added.contains(bookId)) {
            true -> R.drawable.ic_baseline_remove_24
            else -> R.drawable.ic_baseline_add_24
        }
    }

    fun manageAdded(bookId: String) {
        when (added.contains(bookId)) {
            true -> added.remove(bookId)
            else -> added.add(bookId)
        }
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

    fun formatPicture(): Drawable {
        val base64: ByteArray = Base64.decode(base64, Base64.DEFAULT)
        return BitmapDrawable(Resources.getSystem(), BitmapFactory.decodeByteArray(base64, 0, base64.size))
    }

    override fun toString(): String {
        return "User(id='$id', pseudo='$pseudo', name='$name', firstName='$firstName', email='$email', birthday='$birthday')"
    }
}