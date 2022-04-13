package com.project.frontMobile.ui.library

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.project.frontMobile.R
import com.project.frontMobile.adapter.IndexAdapter
import com.project.frontMobile.adapter.LibraryAdapter
import com.project.frontMobile.adapter.LibraryAdapter.LibraryListener
import com.project.frontMobile.data.model.Book
import com.project.frontMobile.data.model.User
import com.project.frontMobile.databinding.FragmentLibraryBinding
import com.project.frontMobile.viewmodel.BookViewModel
import com.project.frontMobile.viewmodel.UserViewModel

class LibraryFragment : Fragment() {

    private lateinit var binding: FragmentLibraryBinding

    private val userViewModel: UserViewModel by viewModels()
    private val bookViewModel: BookViewModel by viewModels()

    /*private var bookList = mutableListOf<Book>()
    private var headerList = mutableListOf<String>()*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_library, container, false)

        userViewModel.findMe("625159f3c00b8d2788aca324")

        val libraryAdapter = LibraryAdapter(LibraryListener { book ->
            val action = LibraryFragmentDirections.actionLibraryFragmentToBookFragment(book.id)
            view?.findNavController()?.navigate(action)
        })

        val manager = GridLayoutManager(activity, 3)

        binding.recyclerViewBook.adapter = libraryAdapter
        binding.recyclerViewBook.layoutManager = manager

        val indexAdapter = IndexAdapter()
        binding.recyclerviewIndex.adapter = indexAdapter

        userViewModel.currentUser.observe(viewLifecycleOwner) {
            bookViewModel.findLibrary(it.library)
        }

        bookViewModel.books.observe(viewLifecycleOwner) {
            if (binding.search.text.toString().length <= 3) {
                val temp = it.sortedBy { book -> book.title.first() }

                libraryAdapter.addHeaderAndSubmitList(getHeader(temp), temp)
                indexAdapter.submitList(getHeader(temp))

                manageSpanSize(manager, libraryAdapter)
            }
        }

        binding.search.doOnTextChanged { text, _, _, _ ->
            val books = bookViewModel.books.value?.sortedBy { book -> book.title.first() }

            if (books != null) {
                when (text?.length!! >= 3) {
                    true -> {
                        val temp = books.filter { it.title.lowercase().contains(text.toString().lowercase()) }

                        libraryAdapter.addHeaderAndSubmitList(getHeader(temp), temp)
                        indexAdapter.submitList(getHeader(temp))
                    }
                    else -> {
                        libraryAdapter.addHeaderAndSubmitList(getHeader(books), books)
                        indexAdapter.submitList(getHeader(books))
                    }
                }
            }

            manageSpanSize(manager, libraryAdapter)
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
        Log.d("Test", "Test")
        val items = adapter.currentList

        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when (items[position]) {
                is LibraryAdapter.DataItem.BookItem -> 1
                else -> 3
            }
        }
    }
}