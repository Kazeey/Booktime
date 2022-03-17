package com.project.frontMobile.data.model

import android.content.res.Resources
import android.graphics.Bitmap
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


    fun decodeBase64(): Drawable {
        val base64: ByteArray = Base64.decode(base64, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(base64, 0, base64.size)

        return BitmapDrawable(Resources.getSystem(), bitmap)
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