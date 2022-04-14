package com.project.frontMobile.ui.book

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.project.frontMobile.R
import com.project.frontMobile.adapter.AuthorAdapter
import com.project.frontMobile.adapter.CategoryAdapter
import com.project.frontMobile.data.model.Book
import com.project.frontMobile.data.model.Status
import com.project.frontMobile.data.model.User
import com.project.frontMobile.databinding.FragmentBookBinding
import com.project.frontMobile.ui.MainActivity
import com.project.frontMobile.utils.RequestCode
import com.project.frontMobile.utils.RequestStatus
import com.project.frontMobile.utils.SnackbarUtils
import com.project.frontMobile.viewmodel.AuthorViewModel
import com.project.frontMobile.viewmodel.BookViewModel
import com.project.frontMobile.viewmodel.UserViewModel

class BookFragment : Fragment() {

    companion object {
        const val BOOK_ID = "bookId"
        const val LIKE = "like"
        const val UNLIKE = "unlike"
        const val ADD = "add"
        const val REMOVE = "remove"
    }

    private lateinit var binding: FragmentBookBinding

    private val bookViewModel: BookViewModel by viewModels()
    private val authorViewModel: AuthorViewModel by activityViewModels()
    private val userViewModel: UserViewModel by viewModels()

    private lateinit var bookId: String

    private lateinit var currentUser: User
    private lateinit var currentBook: Book

    private lateinit var action: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bookViewModel = bookViewModel
        binding.userViewModel = userViewModel
        binding.authorViewModel = authorViewModel
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner

        arguments?.let {
            bookId = it.getString(BOOK_ID) as String
        }

        userViewModel.findMe("625159f3c00b8d2788aca324")

        bookViewModel.currentBook.observe(viewLifecycleOwner) {
            currentBook = it

            (activity as MainActivity).supportActionBar?.title = it.title

            val recyclerView = view.findViewById<RecyclerView>(R.id.category_recyclerView)
            recyclerView.adapter = CategoryAdapter(requireContext(), it.category)
        }

        authorViewModel.authors.observe(viewLifecycleOwner) {
            val recyclerView = view.findViewById<RecyclerView>(R.id.author_recyclerView)
            val manager = GridLayoutManager(activity, 3)
            recyclerView.adapter = AuthorAdapter(requireContext(), it)
            recyclerView.layoutManager = manager
        }

        userViewModel.currentUser.observe(viewLifecycleOwner) {
            currentUser = it

            bookViewModel.getBookById(bookId)
            authorViewModel.getAuthorsByBookId(bookId)
        }

        userViewModel.status.observe(viewLifecycleOwner) {
            binding.loading.visibility = View.GONE

            when (it.statusCode) {
                RequestStatus.STATUS_OK -> {
                    if (it.requestCode == RequestCode.REQUEST_CODE_UPDATE_USER) {
                        manageAction()
                    }
                }
                else -> SnackbarUtils().showSnackbar(
                    requireContext(),
                    binding.coordinator,
                    getString(R.string.error_occurred),
                    Snackbar.LENGTH_LONG
                )
            }
        }
    }

    fun onClick(view: View) {
        binding.loading.visibility = View.VISIBLE
        when (view.id) {
            R.id.add_container -> {
                when (currentBook.isAdded(currentUser.library)) {
                    true -> {
                        action = REMOVE
                        currentUser.library.remove(currentBook.id)
                    }
                    else -> {
                        action = ADD
                        currentUser.library.add(currentBook.id)
                    }
                }
            }
            R.id.like_container -> {
                when (currentBook.isLiked(currentUser.liked)) {
                    true -> {
                        action = UNLIKE
                        currentUser.liked.remove(currentBook.id)
                    }
                    else -> {
                        action = LIKE
                        currentUser.liked.add(currentBook.id)
                    }
                }
            }
        }

        userViewModel.updateUser(currentUser)
    }

    private fun manageAction() {
        val message = when (action) {
            LIKE -> getString(R.string.book_like)
            UNLIKE -> getString(R.string.book_unlike)
            ADD -> getString(R.string.book_add)
            else -> getString(R.string.book_remove)
        }

        SnackbarUtils().showPositiveSnackbar(
            requireContext(),
            binding.coordinator,
            message,
            Snackbar.LENGTH_LONG)
    }
}