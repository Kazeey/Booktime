package com.project.frontMobile.data.model

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import java.io.Serializable

class Book(
    var id: String,
    var title: String,
    var synopsis: String,
    var category: List<String>,
    var authorsId: List<String>,
    var base64: String
    ): Serializable {

    fun formatPicture(): Drawable {
        val base64: ByteArray = Base64.decode(base64, Base64.DEFAULT)
        return BitmapDrawable(Resources.getSystem(), BitmapFactory.decodeByteArray(base64, 0, base64.size))
    }

    fun isLiked(liked: List<String>): Boolean {
        return liked.contains(id)
    }

    fun isAdded(library: List<String>): Boolean {
        return library.contains(id)
    }

    override fun toString(): String {
        return "{\n" +
                "  id='$id',\n" +
                "  title='$title',\n" +
                "  synopsis='$synopsis',\n" +
                "  category=$category,\n" +
                "  authorsId=$authorsId'\n" +
                "}"
    }
}