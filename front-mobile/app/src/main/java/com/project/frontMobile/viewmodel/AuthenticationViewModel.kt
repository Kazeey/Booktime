package com.project.frontMobile.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.project.frontMobile.ui.authentication.login.LogInFragmentDirections

class AuthenticationViewModel: ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    fun onEmailChanged(input: CharSequence) {
        _email.value = input.trim().toString()
    }

    fun onPasswordChanged(input: CharSequence) {
        _password.value = input.trim().toString()
    }

    fun signIn(view: View) {
        if (email.value != null && password.value != null) {
            val action = LogInFragmentDirections.actionLogInFragmentToLibraryFragment()
            view.findNavController().navigate(action)
        }
    }

    fun forgotPassword(view: View) {
        val action = LogInFragmentDirections.actionLogInFragmentToForgotPasswordFragment()
        view.findNavController().navigate(action)
    }

    fun backPressed(view: View) {
        val action = LogInFragmentDirections.actionLogInFragmentToAuthenticationFragment()
        view.findNavController().navigate(action)
    }

    fun clearCache() {
        _email.value = null
        _password.value = null
    }
}