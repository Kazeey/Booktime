package com.project.frontMobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.frontMobile.data.converter.AuthorConverter
import com.project.frontMobile.data.model.Author
import com.project.frontMobile.network.service.BookTimeApi
import kotlinx.coroutines.launch

class AuthorViewModel: ViewModel() {

    private val _currentAuthor = MutableLiveData<Author>()
    val currentAuthor: LiveData<Author>
        get() = _currentAuthor

    private val _authors = MutableLiveData<List<Author>>()
    val authors: MutableLiveData<List<Author>>
        get() = _authors

    fun getAllAuthors() {
        viewModelScope.launch {
            val listResult = BookTimeApi.retrofitService.getAuthors()
            _authors.value = AuthorConverter().convertAll(listResult)

            Log.d(BookViewModel::class.java.name, "Nb of authors : ${authors.value?.size}")
        }
    }

    fun getAuthorsByBookId(bookId: String) {
        viewModelScope.launch {
            val listResult = BookTimeApi.retrofitService.getAuthorsByBookId(bookId)
            _authors.value = AuthorConverter().convertAll(listResult)

            Log.d(BookViewModel::class.java.name, "Nb of authors : ${authors.value?.size}")
        }
    }

    fun getAuthorById(id: String) {
        viewModelScope.launch {
            val authorResult = BookTimeApi.retrofitService.getAuthorById(id)
            _currentAuthor.value = AuthorConverter().convert(authorResult)

            Log.d(BookViewModel::class.java.name, "Current Author : ${currentAuthor.value?.id}")
        }
    }
}