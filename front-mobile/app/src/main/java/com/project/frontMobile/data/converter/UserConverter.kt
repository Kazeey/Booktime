package com.project.frontMobile.data.converter

import android.util.Log
import com.project.frontMobile.data.model.User
import com.project.frontMobile.network.request.UserRequest
import com.project.frontMobile.network.response.UserResponse

class UserConverter {

    fun convert(response: UserResponse): User {
        val library = LibraryConverter().initLibrary(response)

        Log.d("UserConvert", library.toString())

        return User(
            response.id,
            response.pseudo,
            response.name,
            response.firstName,
            response.email,
            library,
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
            LibraryConverter().booksToBooksId(user.library.bookList),
            LibraryConverter().booksToBooksId(user.library.likedList),
            user.base64
        )
    }
}