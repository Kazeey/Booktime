package com.project.frontMobile.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.project.frontMobile.R
import com.project.frontMobile.data.converter.UserConverter
import com.project.frontMobile.data.model.User
import com.project.frontMobile.network.request.SignUpRequest
import com.project.frontMobile.network.service.BookTimeApi
import com.project.frontMobile.ui.authentication.login.LogInFragmentDirections
import kotlinx.coroutines.launch
import java.lang.Exception

class AuthenticationViewModel: ViewModel() {

    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User>
        get() = _currentUser

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = BookTimeApi.retrofitService.signUp(SignUpRequest(email, password))
                _currentUser.value = UserConverter().convert(result)

                _status.value = "OK"
            } catch (e: Exception) {
                _status.value = "FAIL"
            }
        }
    }

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

    fun onClick(view: View) {
        val action = when (view.id) {
            R.id.log_in_button -> LogInFragmentDirections.actionLogInFragmentToLibraryFragment()
            R.id.forgot_password_button -> LogInFragmentDirections.actionLogInFragmentToForgotPasswordFragment()
            R.id.back_nav_log_in -> LogInFragmentDirections.actionLogInFragmentToAuthenticationFragment()
            else -> LogInFragmentDirections.actionLogInFragmentToAuthenticationFragment()
        }
        view.findNavController().navigate(action)
    }
}