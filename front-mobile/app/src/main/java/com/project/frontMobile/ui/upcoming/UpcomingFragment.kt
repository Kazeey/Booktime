package com.project.frontMobile.ui.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.project.frontMobile.R
import com.project.frontMobile.adapter.UpComingAdapter
import com.project.frontMobile.adapter.UpComingAdapter.UpComingListener
import com.project.frontMobile.databinding.FragmentUpcomingBinding
import com.project.frontMobile.utils.RequestStatus
import com.project.frontMobile.utils.SnackbarUtils
import com.project.frontMobile.viewmodel.BookViewModel

class UpcomingFragment : Fragment() {

    private lateinit var binding: FragmentUpcomingBinding

    private val viewModel: BookViewModel by viewModels()

    private lateinit var adapter: UpComingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.fragment = this

        setupRecyclerView()
        viewModel.getUpComingBooks()

        viewModel.books.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it.sortedBy { book -> book.publicationDate })
            }
        }

        viewModel.status.observe(viewLifecycleOwner) {
            binding.loading.visibility = View.GONE

            when (it.statusCode) {
                RequestStatus.STATUS_OK -> {}
                else -> SnackbarUtils().showSnackbar(
                    requireContext(),
                    binding.coordinator,
                    getString(R.string.error_occurred),
                    Snackbar.LENGTH_LONG
                )
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = UpComingAdapter(UpComingListener { bookId ->
            val action = UpcomingFragmentDirections.actionUpcomingFragmentToBookFragment(bookId)
            view?.findNavController()?.navigate(action)
        })
        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager
    }

    fun onSearchChanged(i: CharSequence) {
        val input = i.trim().toString().lowercase()
        if (input.length > 2) {
            adapter.submitList(viewModel.books.value?.filter { it.title.lowercase().contains(input)})
        } else {
            adapter.submitList(viewModel.books.value)
        }
    }
}