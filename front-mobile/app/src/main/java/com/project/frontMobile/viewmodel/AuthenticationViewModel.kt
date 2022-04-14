package com.project.frontMobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.frontMobile.data.converter.UserConverter
import com.project.frontMobile.data.model.Status
import com.project.frontMobile.data.model.User
import com.project.frontMobile.network.request.LogInRequest
import com.project.frontMobile.network.request.SignUpRequest
import com.project.frontMobile.network.service.BookTimeApi
import com.project.frontMobile.utils.RequestCode
import com.project.frontMobile.utils.RequestStatus
import kotlinx.coroutines.launch
import java.lang.Exception

class AuthenticationViewModel: ViewModel() {

    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User>
        get() = _currentUser

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = BookTimeApi.retrofitService.signUp(SignUpRequest(email, password))
                _currentUser.value = UserConverter().convert(result)

                _status.value = Status(RequestStatus.STATUS_OK, RequestCode.REQUEST_CODE_SIGN_UP)
            } catch (e: Exception) {
                _status.value = Status(RequestStatus.STATUS_FAIL, RequestCode.REQUEST_CODE_SIGN_UP)
            }
        }
    }

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = BookTimeApi.retrofitService.logIn(LogInRequest(email, password))
                _currentUser.value = UserConverter().convert(result)

                _status.value = Status(RequestStatus.STATUS_OK, RequestCode.REQUEST_CODE_LOG_IN)
            } catch (e: Exception) {
                e.message?.let {
                    when (it.contains(RequestStatus.STATUS_NOT_FOUND.toString())) {
                        true -> _status.value = Status(RequestStatus.STATUS_NOT_FOUND, RequestCode.REQUEST_CODE_LOG_IN)
                        else ->  _status.value = Status(RequestStatus.STATUS_FAIL, RequestCode.REQUEST_CODE_LOG_IN)
                    }
                }
            }
        }
    }
}