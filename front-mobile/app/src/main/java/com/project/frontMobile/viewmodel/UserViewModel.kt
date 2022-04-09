package com.project.frontMobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.frontMobile.data.converter.UserConverter
import com.project.frontMobile.data.model.User
import com.project.frontMobile.network.service.BookTimeApi
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel: ViewModel() {

    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User>
        get() = _currentUser

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    fun getUserById(id: String) {
        viewModelScope.launch {
            try {
                val userResult = BookTimeApi.retrofitService.getUserById(id)
                _currentUser.value = UserConverter().convert(userResult)

                _status.value = "OK"

                Log.d(UserViewModel::class.java.name, "Current User : ${currentUser.value?.toString()}")
            } catch (e: Exception) {
                _status.value = "FAIL"
            }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            val userResult = BookTimeApi.retrofitService.updateUser(user.id, user)
            _currentUser.value = UserConverter().convert(userResult)

            Log.d(UserViewModel::class.java.name, "Current User Updated : ${currentUser.value?.toString()}")
        }
    }
}