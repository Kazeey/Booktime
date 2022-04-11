package com.project.frontMobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.frontMobile.data.converter.UserConverter
import com.project.frontMobile.data.model.User
import com.project.frontMobile.network.request.LogInRequest
import com.project.frontMobile.network.request.SignUpRequest
import com.project.frontMobile.network.service.BookTimeApi
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

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = BookTimeApi.retrofitService.logIn(LogInRequest(email, password))
                _currentUser.value = UserConverter().convert(result)

                _status.value = "OK"
            } catch (e: Exception) {
                e.message?.let {
                    when (it.contains("404")) {
                        true -> _status.value = "NOT_FOUND"
                        else ->  _status.value = "FAIL"
                    }
                }
            }
        }
    }

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    fun clearStatus() {
        _status.value = null
    }
}