package com.project.frontMobile.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.project.frontMobile.R

class SnackbarUtils {

    fun create(context: Context, view: View, strResource: Int, duration: Int, state: String): Snackbar {
        val snackbar = Snackbar.make(view, strResource, duration)

        snackbar.setBackgroundTint(getBackgroundColor(context, state))

        return snackbar
    }

    private fun getBackgroundColor(context: Context, state: String): Int {
        return when (state) {
            "Success" -> ContextCompat.getColor(context, R.color.green)
            "Failure" -> ContextCompat.getColor(context, R.color.red)
            else -> ContextCompat.getColor(context, R.color.black)
        }
    }
}