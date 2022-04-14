package com.project.frontMobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.frontMobile.data.converter.UserConverter
import com.project.frontMobile.data.model.Status
import com.project.frontMobile.data.model.User
import com.project.frontMobile.network.service.BookTimeApi
import com.project.frontMobile.utils.RequestCode
import com.project.frontMobile.utils.RequestStatus
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel: ViewModel() {

    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User>
        get() = _currentUser

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    fun findMe() {
        viewModelScope.launch {
            try {
                val userResult = BookTimeApi.retrofitService.getUserById("625159f3c00b8d2788aca324")
                _currentUser.value = UserConverter().convert(userResult)

                Log.d(UserViewModel::class.java.name, "Current User : ${currentUser.value?.toString()}")

                _status.value = Status(RequestStatus.STATUS_OK, RequestCode.REQUEST_CODE_FIND_ME)
            } catch (e: Exception) {
                e.message?.let {
                    when (it.contains(RequestStatus.STATUS_NOT_FOUND.toString())) {
                        true -> _status.value = Status(RequestStatus.STATUS_NOT_FOUND, RequestCode.REQUEST_CODE_FIND_ME)
                        else ->  _status.value = Status(RequestStatus.STATUS_FAIL, RequestCode.REQUEST_CODE_FIND_ME)
                    }
                }
            }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            try {
                val userResult = BookTimeApi.retrofitService.updateUser(user.id, UserConverter().convert(user))
                _currentUser.value = UserConverter().convert(userResult)

                Log.d(UserViewModel::class.java.name, "Current User Updated : ${currentUser.value?.toString()}")

                _status.value = Status(RequestStatus.STATUS_OK, RequestCode.REQUEST_CODE_UPDATE_USER)
            } catch (e: Exception) {
                e.message?.let {
                    when (it.contains(RequestStatus.STATUS_NOT_FOUND.toString())) {
                        true -> _status.value = Status(RequestStatus.STATUS_NOT_FOUND, RequestCode.REQUEST_CODE_UPDATE_USER)
                        else ->  _status.value = Status(RequestStatus.STATUS_FAIL, RequestCode.REQUEST_CODE_UPDATE_USER)
                    }
                }
            }
        }
    }
}