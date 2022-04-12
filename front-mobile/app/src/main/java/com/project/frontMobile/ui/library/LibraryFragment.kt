package com.project.frontMobile.ui.library

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.project.frontMobile.R
import com.project.frontMobile.adapter.IndexAdapter
import com.project.frontMobile.adapter.LibraryAdapter
import com.project.frontMobile.adapter.LibraryAdapter.LibraryListener
import com.project.frontMobile.data.model.Book
import com.project.frontMobile.databinding.FragmentLibraryBinding
import com.project.frontMobile.viewmodel.UserViewModel

class LibraryFragment : Fragment() {
    private lateinit var binding: FragmentLibraryBinding

    var bookList = mutableListOf<Book>()
    var headerList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_library, container, false)
        val viewModel: UserViewModel by activityViewModels()

        val libraryAdapter = LibraryAdapter(LibraryListener { book ->
            val action = LibraryFragmentDirections.actionLibraryFragmentToBookFragment(book.id)
            view?.findNavController()?.navigate(action)
        })

        val manager = GridLayoutManager(activity, 3)

        binding.recyclerViewBook.adapter = libraryAdapter
        binding.recyclerViewBook.layoutManager = manager

        val indexAdapter = IndexAdapter()
        binding.recyclerviewIndex.adapter = indexAdapter

        viewModel.currentUser.observe(viewLifecycleOwner) {
            it?.let {
                /*bookList.addAll(it.library.bookList.sortedBy { book -> book.title.first() })
                headerList.addAll(getHeader(bookList))

                Log.d("LibraryFragmentBookLsit", "${bookList.size}")

                libraryAdapter.addHeaderAndSubmitList(headerList, bookList)
                indexAdapter.submitList(headerList)
                manageSpanSize(manager, libraryAdapter)*/
            }
        }

        binding.search.doOnTextChanged { text, start, before, count ->
            val books = mutableListOf<Book>()
            val headers = mutableListOf<String>()

            when (text?.length!! >= 3) {
                true -> {
                    books.addAll(bookList.filter { it.title.lowercase().contains(text.toString().lowercase()) })
                    headers.addAll(getHeader(books))

                    libraryAdapter.addHeaderAndSubmitList(
                        headers,
                        books
                    )
                    indexAdapter.submitList(headers)
                }
                else -> when (text.length) {
                    0 -> {
                        books.addAll(bookList)
                        headers.addAll(headerList)

                        libraryAdapter.addHeaderAndSubmitList(headers, books)
                        indexAdapter.submitList(headers)
                    }
                    else -> {}
                }
            }
        }

        return binding.root
    }

    private fun getHeader(dataset: List<Book>): List<String> {
        val results = mutableListOf<String>()

        for (data: Book in dataset) {
            results.add(data.title.first().uppercase())
        }

        return results.distinct().sorted()
    }

    private fun manageSpanSize(manager: GridLayoutManager, adapter: LibraryAdapter) {
        val items = adapter.currentList
        Log.d("LibraryFragment", "${adapter.currentList.size}")

        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when (items[position]) {
                is LibraryAdapter.DataItem.BookItem -> 1
                else -> 3
            }
        }
    }
}