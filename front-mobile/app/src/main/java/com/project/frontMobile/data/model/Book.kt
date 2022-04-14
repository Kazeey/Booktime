package com.project.frontMobile.data.model

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.Serializable
import android.os.Build
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.project.frontMobile.R
import jp.wasabeef.glide.transformations.BlurTransformation
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class Book(
    var id: String,
    var title: String,
    var synopsis: String,
    var category: List<String>,
    var authorsId: List<String>,
    var publicationDate: String,
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun daysLeft(): Long {
        val today = LocalDateTime.now()
        val endDate = LocalDateTime.parse(
            publicationDate.replace("T", " ").substring(0, 19),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        )

        return Duration.between(today, endDate).toDays()
    }
}