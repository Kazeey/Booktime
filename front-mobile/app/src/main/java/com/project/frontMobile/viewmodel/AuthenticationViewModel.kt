package com.project.frontMobile.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.project.frontMobile.R
import com.project.frontMobile.ui.authentication.login.LogInFragmentDirections
import com.project.frontMobile.ui.authentication.signup.SignUpFragmentDirections

class AuthenticationViewModel: ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _confirmEmail = MutableLiveData<String>()
    val confirmEmail: LiveData<String>
        get() = _confirmEmail

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    fun onEmailChanged(input: CharSequence) {
        _email.value = input.trim().toString()
    }

    fun onConfirmEmailChanged(input: CharSequence) {
        _confirmEmail.value = input.trim().toString()
    }

    fun onPasswordChanged(input: CharSequence) {
        _password.value = input.trim().toString()
    }

    fun onClick(view: View) {
        val action = when (view.id) {
            R.id.sign_up_button -> SignUpFragmentDirections.actionSignUpFragmentToLibraryFragment()
            R.id.back_nav_sign_up -> SignUpFragmentDirections.actionSignUpFragmentToAuthenticationFragment()
            R.id.log_in_button -> LogInFragmentDirections.actionLogInFragmentToLibraryFragment()
            R.id.forgot_password_button -> LogInFragmentDirections.actionLogInFragmentToForgotPasswordFragment()
            R.id.back_nav_log_in -> LogInFragmentDirections.actionLogInFragmentToAuthenticationFragment()
            else -> LogInFragmentDirections.actionLogInFragmentToAuthenticationFragment()
        }
        view.findNavController().navigate(action)
    }

    fun clearCache() {
        _email.value = null
        _password.value = null
    }
}