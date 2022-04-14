package com.project.frontMobile.data.converter

import android.util.Log
import com.project.frontMobile.data.model.User
import com.project.frontMobile.network.request.UserRequest
import com.project.frontMobile.network.response.UserResponse

class UserConverter {

    fun convert(response: UserResponse): User {
        return User(
            response.id,
            response.pseudo,
            response.name,
            response.firstName,
            response.email,
            if (response.library.isNullOrEmpty()) mutableListOf() else response.library,
            if (response.liked.isNullOrEmpty()) mutableListOf() else response.liked,
            response.base64
        )
    }

    fun convertAll(responses: List<UserResponse>): List<User> {
        val userList = mutableListOf<User>()

        for (response: UserResponse in responses) {
            userList.add(convert(response))
        }

        return userList
    }

    fun convert(user: User): UserRequest {
        return UserRequest(
            user.id,
            user.pseudo,
            user.name,
            user.firstName,
            user.email,
            user.library,
            user.liked,
            user.base64
        )
    }
}