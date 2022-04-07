package com.project.frontMobile.ui.upcoming

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
import com.project.frontMobile.adapter.UpComingAdapter
import com.project.frontMobile.adapter.UpComingAdapter.UpComingListener
import com.project.frontMobile.databinding.FragmentUpcomingBinding
import com.project.frontMobile.viewmodel.BookViewModel

class UpcomingFragment : Fragment() {

    private lateinit var binding: FragmentUpcomingBinding

    private val viewModel: BookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        viewModel.getUpComingBooks()

        val adapter = UpComingAdapter(UpComingListener { bookId ->
            val action = UpcomingFragmentDirections.actionUpcomingFragmentToBookFragment(bookId)
            view?.findNavController()?.navigate(action)
        })
        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager

        viewModel.books.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it.sortedBy { book -> book.publicationDate })
            }
        }

        binding.search.doOnTextChanged { text, _, _, _ ->
            Log.d("Test", text.toString())
        }
    }
}