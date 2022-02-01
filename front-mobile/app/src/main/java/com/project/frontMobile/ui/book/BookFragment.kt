package com.project.frontMobile.ui.book

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.project.frontMobile.R
import com.project.frontMobile.adapter.AuthorAdapter
import com.project.frontMobile.databinding.FragmentBookBinding
import com.project.frontMobile.ui.MainActivity
import com.project.frontMobile.utils.ClickHandler
import com.project.frontMobile.viewmodel.AuthorViewModel
import com.project.frontMobile.viewmodel.BookViewModel

class BookFragment : Fragment() {

    companion object {
        const val BOOK_ID = "book_id"
    }

    private lateinit var binding: FragmentBookBinding

    private val bookViewModel: BookViewModel by viewModels()
    private val authorViewModel: AuthorViewModel by activityViewModels()

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

        binding.viewModel = bookViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        arguments?.let {
            bookId = it.getString(BOOK_ID).toString()
        }

        bookViewModel.init(bookId)

        bookViewModel.currentBook.observe(viewLifecycleOwner, { book ->
            (activity as MainActivity).supportActionBar?.title = book.title

            val base64: ByteArray = Base64.decode(book.base64, Base64.DEFAULT)
            binding.bookImage.setImageBitmap(BitmapFactory.decodeByteArray(base64, 0, base64.size))

            authorViewModel.getAllAuthors()
        })

        authorViewModel.authors.observe(viewLifecycleOwner, { authors ->
            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.adapter = AuthorAdapter(requireContext(), authors)
        })
    }
}