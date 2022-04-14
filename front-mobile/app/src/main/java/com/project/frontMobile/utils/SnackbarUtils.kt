package com.project.frontMobile.utils

import android.content.Context
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.project.frontMobile.R

class SnackbarUtils {

    fun showSnackbar(context: Context, coordinatorLayout: CoordinatorLayout, message: String, duration: Int) {
        val snackbar = Snackbar.make(coordinatorLayout, message, duration)
        snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.red))
        if (duration == Snackbar.LENGTH_INDEFINITE) {
            snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.white))
            snackbar.setAction(R.string.button_ok) {
                snackbar.dismiss()
            }
        }
        snackbar.show()
    }

    fun showPositiveSnackbar(context: Context, coordinatorLayout: CoordinatorLayout, message: String, duration: Int) {
        val snackbar = Snackbar.make(coordinatorLayout, message, duration)
        snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.green))
        if (duration == Snackbar.LENGTH_INDEFINITE) {
            snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.white))
            snackbar.setAction(R.string.button_ok) {
                snackbar.dismiss()
            }
        }
        snackbar.show()
    }

}