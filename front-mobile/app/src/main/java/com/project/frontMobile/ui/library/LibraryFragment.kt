package com.project.frontMobile.ui.library

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.project.frontMobile.R
import com.project.frontMobile.adapter.LibraryAdapter
import com.project.frontMobile.adapter.LibraryAdapter.LibraryListener
import com.project.frontMobile.data.model.Book
import com.project.frontMobile.databinding.FragmentLibraryBinding
import com.project.frontMobile.viewmodel.UserViewModel

class LibraryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLibraryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_library, container, false)
        val viewModel: UserViewModel by activityViewModels()

        val adapter = LibraryAdapter(LibraryListener { bookId ->
            val action = LibraryFragmentDirections.actionLibraryFragmentToBookFragment(bookId)
            view?.findNavController()?.navigate(action)
        })

        val manager = GridLayoutManager(activity, 3)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager

        viewModel.currentUser.observe(viewLifecycleOwner) {
            it?.let {
                val sortedList = it.library.sortedBy { book -> book.title.first() }
                adapter.addHeaderAndSubmitList(getHeader(sortedList), sortedList)

                manageSpanSize(manager, adapter)
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
        val items = adapter.items

        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when (items[position]) {
                is LibraryAdapter.DataItem.BookItem -> 1
                else -> 3
            }
        }
    }
}