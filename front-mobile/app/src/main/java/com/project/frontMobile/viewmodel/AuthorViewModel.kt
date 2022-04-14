package com.project.frontMobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.frontMobile.data.converter.AuthorConverter
import com.project.frontMobile.data.model.Author
import com.project.frontMobile.data.model.Status
import com.project.frontMobile.network.service.BookTimeApi
import com.project.frontMobile.utils.RequestCode
import com.project.frontMobile.utils.RequestStatus
import kotlinx.coroutines.launch

class AuthorViewModel: ViewModel() {

    private val _currentAuthor = MutableLiveData<Author>()
    val currentAuthor: LiveData<Author>
        get() = _currentAuthor

    private val _authors = MutableLiveData<List<Author>>()
    val authors: MutableLiveData<List<Author>>
        get() = _authors

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    fun getAuthorsByBookId(bookId: String) {
        viewModelScope.launch {
            try {
                val listResult = BookTimeApi.retrofitService.getAuthorsByBookId(bookId)
                _authors.value = AuthorConverter().convertAll(listResult)

                Log.d(BookViewModel::class.java.name, "Nb of authors : ${authors.value?.size}")

                _status.value = Status(RequestStatus.STATUS_OK, RequestCode.REQUEST_CODE_FIND_AUTHOR_LIST)
            } catch (e: Exception) {
                e.message?.let {
                    when (it.contains(RequestStatus.STATUS_NOT_FOUND.toString())) {
                        true -> _status.value = Status(RequestStatus.STATUS_NOT_FOUND, RequestCode.REQUEST_CODE_FIND_AUTHOR_LIST)
                        else ->  _status.value = Status(RequestStatus.STATUS_FAIL, RequestCode.REQUEST_CODE_FIND_AUTHOR_LIST)
                    }
                }
            }
        }
    }

    fun getAuthorById(id: String) {
        viewModelScope.launch {
            try {
                val authorResult = BookTimeApi.retrofitService.getAuthorById(id)
                _currentAuthor.value = AuthorConverter().convert(authorResult)

                Log.d(BookViewModel::class.java.name, "Current Author : ${currentAuthor.value?.id}")

                _status.value = Status(RequestStatus.STATUS_OK, RequestCode.REQUEST_CODE_FIND_AUTHOR)
            } catch (e: Exception) {
                e.message?.let {
                    when (it.contains(RequestStatus.STATUS_NOT_FOUND.toString())) {
                        true -> _status.value = Status(RequestStatus.STATUS_NOT_FOUND, RequestCode.REQUEST_CODE_FIND_AUTHOR)
                        else ->  _status.value = Status(RequestStatus.STATUS_FAIL, RequestCode.REQUEST_CODE_FIND_AUTHOR)
                    }
                }
            }
        }
    }
}