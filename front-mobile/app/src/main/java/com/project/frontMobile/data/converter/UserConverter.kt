package com.project.frontMobile.data.converter

import com.project.frontMobile.data.model.User
import com.project.frontMobile.network.response.UserResponse

class UserConverter {

    fun convert(response: UserResponse): User {
        return User(
            response.id,
            response.pseudo,
            response.name,
            response.firstName,
            response.email,
            response.birthday,
            response.added,
            response.liked,
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
}