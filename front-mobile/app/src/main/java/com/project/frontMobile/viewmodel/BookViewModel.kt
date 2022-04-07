package com.project.frontMobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.frontMobile.data.converter.BookConverter
import com.project.frontMobile.data.model.Book
import com.project.frontMobile.network.service.BookTimeApi
import kotlinx.coroutines.launch

class BookViewModel: ViewModel() {

    private val _currentBook = MutableLiveData<Book>()
    val currentBook: LiveData<Book>
        get() = _currentBook

    private val _books = MutableLiveData<List<Book>>()
    val books: MutableLiveData<List<Book>>
        get() = _books

    private val _tempBooks = MutableLiveData<List<Book>>()
    val tempBooks: MutableLiveData<List<Book>>
        get() = _tempBooks

    fun getAllBooks() {
        viewModelScope.launch {
            val listResult = BookTimeApi.retrofitService.getBooks()
            _books.value = BookConverter().convertAll(listResult)

            Log.d(BookViewModel::class.java.name, "Nb of books : ${books.value?.size}")
        }
    }

    fun getUpComingBooks() {
        viewModelScope.launch {
            val listResult = BookTimeApi.retrofitService.getUpComing()
            _books.value = BookConverter().convertAll(listResult)
            _tempBooks.value = BookConverter().convertAll(listResult)

            Log.d(BookViewModel::class.java.name, "Nb of books : ${books.value?.size}")
        }
    }

    fun getBookById(id: String) {
        viewModelScope.launch {
            val bookResult = BookTimeApi.retrofitService.getBookById(id)
            _currentBook.value = BookConverter().convert(bookResult)

            Log.d(BookViewModel::class.java.name, "Current Book : ${currentBook.value?.id}")
        }
    }

    fun onSearchChanged(input: CharSequence) {
        if (input.trim().toString().length > 2) {
            _books.value = books.value?.filter { it.title.contains(input) }
        } else {
            if (books.value?.size != tempBooks.value?.size)
                _books.value = tempBooks.value
        }
    }
}