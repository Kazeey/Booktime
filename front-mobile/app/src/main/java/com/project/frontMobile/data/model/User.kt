package com.project.frontMobile.data.model

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import androidx.core.content.ContextCompat
import com.project.frontMobile.R

class User(
    var id: String,
    var pseudo: String,
    var name: String,
    var firstName: String,
    var email: String,
    var birthday: String,
    var library: MutableList<String>,
    var liked: MutableList<String>,
    var base64: String,
) {
    override fun toString(): String {
        return "User(id='$id', pseudo='$pseudo', name='$name', firstName='$firstName', email='$email', library=$library, liked=$liked)"
    }

    fun formatPicture(): Drawable {
        val base64: ByteArray = Base64.decode(base64, Base64.DEFAULT)
        return BitmapDrawable(Resources.getSystem(), BitmapFactory.decodeByteArray(base64, 0, base64.size))
    }

    override fun toString(): String {
        return "User(id='$id', pseudo='$pseudo', name='$name', firstName='$firstName', email='$email', birthday='$birthday')"
    }
}