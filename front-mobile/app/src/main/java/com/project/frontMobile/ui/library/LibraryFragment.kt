package com.project.frontMobile.ui.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.project.frontMobile.R
import com.project.frontMobile.adapter.IndexAdapter
import com.project.frontMobile.adapter.LibraryAdapter
import com.project.frontMobile.adapter.LibraryAdapter.LibraryListener
import com.project.frontMobile.data.model.Book
import com.project.frontMobile.databinding.FragmentLibraryBinding
import com.project.frontMobile.viewmodel.BookViewModel
import com.project.frontMobile.viewmodel.UserViewModel

class LibraryFragment : Fragment() {

    private lateinit var binding: FragmentLibraryBinding

    private val userViewModel: UserViewModel by viewModels()
    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_library, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.findMe("625159f3c00b8d2788aca324")

        val libraryAdapter = LibraryAdapter(LibraryListener { book ->
            val action = LibraryFragmentDirections.actionLibraryFragmentToBookFragment(book.id)
            view.findNavController().navigate(action)
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
            val temp = it.sortedBy { book -> book.title.first() }

            libraryAdapter.addHeaderAndSubmitList(getHeader(temp), temp)
            indexAdapter.submitList(getHeader(temp))

            manageSpanSize(manager, libraryAdapter)
        }
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

        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when (items[position]) {
                is LibraryAdapter.DataItem.BookItem -> 1
                else -> 3
            }
        }
    }
}