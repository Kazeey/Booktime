package com.project.frontMobile.ui.book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.project.frontMobile.R
import com.project.frontMobile.databinding.FragmentBookBinding
import com.project.frontMobile.ui.MainActivity
import com.project.frontMobile.utils.ClickHandler
import com.project.frontMobile.viewmodel.BookViewModel

class BookFragment : Fragment(), ClickHandler {

    companion object {
        const val BOOK_ID = "book_id"
    }

    private lateinit var binding: FragmentBookBinding

    private val viewModel: BookViewModel by viewModels()

    private lateinit var bookId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.handler = this

        arguments?.let {
            bookId = it.getString(BOOK_ID).toString()
        }

        viewModel.init(bookId)

        viewModel.currentBookResponse.observe(viewLifecycleOwner, { book ->
            (activity as MainActivity).supportActionBar?.title = book.title
        })
    }

    override fun onClick(view: View) {
        val action = BookFragmentDirections.actionBookFragmentToAuthorFragment()
        view.findNavController().navigate(action)
    }
}