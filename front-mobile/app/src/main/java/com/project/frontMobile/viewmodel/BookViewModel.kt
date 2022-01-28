package com.project.frontMobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.frontMobile.data.converter.BookConverter
import com.project.frontMobile.data.model.Book
import com.project.frontMobile.network.service.BookApi
import kotlinx.coroutines.launch

class BookViewModel: ViewModel() {

    private val _currentBook = MutableLiveData<Book>()
    val currentBookResponse: LiveData<Book>
        get() = _currentBook

    private val _books = MutableLiveData<List<Book>>()
    val books: MutableLiveData<List<Book>>
        get() = _books

    fun init(id: String) {
        getBookById(id)
        getAllBooks()
    }

    fun getAllBooks() {
        viewModelScope.launch {
            val listResult = BookApi.retrofitService.getBooks()
            _books.value = BookConverter().convertAll(listResult)

            Log.d(BookViewModel::class.java.name, "Nb of books : ${books.value?.size}")
        }
    }

    fun getBookById(id: String) {
        viewModelScope.launch {
            val bookResult = BookApi.retrofitService.getBookById(id)
            _currentBook.value = BookConverter().convert(bookResult)

            Log.d(BookViewModel::class.java.name, "Current Book : ${currentBookResponse.value?.title}")
        }
    }
}