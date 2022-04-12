package com.project.frontMobile.ui.book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.project.frontMobile.R
import com.project.frontMobile.adapter.AuthorAdapter
import com.project.frontMobile.adapter.CategoryAdapter
import com.project.frontMobile.data.model.Book
import com.project.frontMobile.data.model.User
import com.project.frontMobile.databinding.FragmentBookBinding
import com.project.frontMobile.ui.MainActivity
import com.project.frontMobile.viewmodel.AuthorViewModel
import com.project.frontMobile.viewmodel.BookViewModel
import com.project.frontMobile.viewmodel.UserViewModel

class BookFragment : Fragment() {

    companion object {
        const val BOOK_ID = "bookId"
    }

    private lateinit var binding: FragmentBookBinding

    private val bookViewModel: BookViewModel by viewModels()
    private val authorViewModel: AuthorViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var currentUser: User
    private lateinit var currentBook: Book

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
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner

        arguments?.let {
            val bookId = it.getString(BOOK_ID) as String
            bookViewModel.getBookById(bookId)
            authorViewModel.getAuthorsByBookId(bookId)
        }

        bookViewModel.currentBook.observe(viewLifecycleOwner) {
            currentBook = it

            (activity as MainActivity).supportActionBar?.title = it.title

            val recyclerView = view.findViewById<RecyclerView>(R.id.category_recyclerView)
            recyclerView.adapter = CategoryAdapter(requireContext(), it.category)
        }

        authorViewModel.authors.observe(viewLifecycleOwner) {
            val recyclerView = view.findViewById<RecyclerView>(R.id.author_recyclerView)
            recyclerView.adapter = AuthorAdapter(requireContext(), it)
        }

        userViewModel.currentUser.observe(viewLifecycleOwner) {
            currentUser = it
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.add_container -> {
                when (currentBook.isAdded(currentUser.library)) {
                    true -> currentUser.library.remove(currentBook.id)
                    else -> currentUser.library.add(currentBook.id)
                }
            }
            R.id.like_container -> {
                when (currentBook.isLiked(currentUser.liked)) {
                    true -> currentUser.liked.remove(currentBook.id)
                    else -> currentUser.liked.add(currentBook.id)
                }
            }
        }

        userViewModel.updateUser(currentUser)
    }
}