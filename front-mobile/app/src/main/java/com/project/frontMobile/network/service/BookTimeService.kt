package com.project.frontMobile.network.service

import com.project.frontMobile.data.model.User
import com.project.frontMobile.network.request.UserRequest
import com.project.frontMobile.network.response.AuthorResponse
import com.project.frontMobile.network.response.BookResponse
import com.project.frontMobile.network.response.UserResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://10.0.2.2:8080"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BookTimeService {

    /**
     * BOOK
     */
    @POST("book/find/library")
    suspend fun findLibrary(@Body booksId: List<String>): List<BookResponse>

    @GET("book/findBy/{id}")
    suspend fun getBookById(@Path("id") id: String): BookResponse

    /**
     * AUTHOR
     */

    @GET("author/findAll")
    suspend fun getAuthors(): List<AuthorResponse>

    @GET("author/findAuthorsByBook/{bookId}")
    suspend fun getAuthorsByBookId(@Path("bookId") bookId: String): List<AuthorResponse>

    @GET("author/findBy/{id}")
    suspend fun getAuthorById(@Path("id") id: String): AuthorResponse

    /**
     * USER
     */

    @GET("user/me/{id}")
    suspend fun findMe(@Path("id") id: String): UserResponse

    @GET("user/findBy/{id}")
    suspend fun getUserById(@Path("id") id: String): UserResponse

    @PATCH("user/update/{id}")
    suspend fun updateUser(@Path("id") id: String,
                           @Body user: UserRequest): UserResponse
}

object BookTimeApi {
    val retrofitService: BookTimeService by lazy {
        retrofit.create(BookTimeService::class.java)
    }
}