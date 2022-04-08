package com.project.frontMobile.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.frontMobile.R
import com.project.frontMobile.data.converter.UserConverter
import com.project.frontMobile.data.model.User
import com.project.frontMobile.network.service.BookTimeApi
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User>
        get() = _currentUser

    fun getUserById(id: String) {
        viewModelScope.launch {
            val userResult = BookTimeApi.retrofitService.getUserById(id)
            _currentUser.value = UserConverter().convert(userResult)

            Log.d(UserViewModel::class.java.name, "Current User : ${currentUser.value?.toString()}")
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