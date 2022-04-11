package com.project.frontMobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.frontMobile.data.converter.BookConverter
import com.project.frontMobile.data.model.Book
import com.project.frontMobile.data.model.Status
import com.project.frontMobile.network.service.BookTimeApi
import com.project.frontMobile.utils.RequestCode
import com.project.frontMobile.utils.RequestStatus
import kotlinx.coroutines.launch
import java.lang.Exception

class BookViewModel: ViewModel() {

    private val _currentBook = MutableLiveData<Book>()
    val currentBook: LiveData<Book>
        get() = _currentBook

    private val _books = MutableLiveData<List<Book>>()
    val books: MutableLiveData<List<Book>>
        get() = _books

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    fun getUpComingBooks() {
        viewModelScope.launch {
            try {
                val listResult = BookTimeApi.retrofitService.getUpComing()
                _books.value = BookConverter().convertAll(listResult)

                Log.d(BookViewModel::class.java.name, "Nb of books : ${books.value?.size}")

                _status.value = Status(RequestStatus.STATUS_OK, RequestCode.REQUEST_CODE_FIND_BOOK)
            } catch (e: Exception) {
                e.message?.let {
                    when (it.contains(RequestStatus.STATUS_NOT_FOUND.toString())) {
                        true -> _status.value = Status(RequestStatus.STATUS_NOT_FOUND, RequestCode.REQUEST_CODE_FIND_BOOK)
                        else ->  _status.value = Status(RequestStatus.STATUS_FAIL, RequestCode.REQUEST_CODE_FIND_BOOK)
                    }
                }
            }
        }
    }

    fun getBookById(id: String) {
        viewModelScope.launch {
            try {
                val bookResult = BookTimeApi.retrofitService.getBookById(id)
                _currentBook.value = BookConverter().convert(bookResult)

                Log.d(BookViewModel::class.java.name, "Current Book : ${currentBook.value?.id}")

                _status.value = Status(RequestStatus.STATUS_OK, RequestCode.REQUEST_CODE_FIND_BOOK)
            } catch (e: Exception) {
                e.message?.let {
                    when (it.contains(RequestStatus.STATUS_NOT_FOUND.toString())) {
                        true -> _status.value = Status(RequestStatus.STATUS_NOT_FOUND, RequestCode.REQUEST_CODE_FIND_BOOK)
                        else ->  _status.value = Status(RequestStatus.STATUS_FAIL, RequestCode.REQUEST_CODE_FIND_BOOK)
                    }
                }
            }

        }
    }
}