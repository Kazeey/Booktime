package com.project.frontMobile.ui.author

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.project.frontMobile.R
import com.project.frontMobile.databinding.FragmentAuthorBinding
import com.project.frontMobile.ui.MainActivity
import com.project.frontMobile.ui.book.BookFragment
import com.project.frontMobile.viewmodel.AuthorViewModel

class AuthorFragment : Fragment() {

    companion object {
        const val AUTHOR_ID = "author_id"
    }

    private lateinit var binding: FragmentAuthorBinding

    private val authorViewModel: AuthorViewModel by activityViewModels()

    private lateinit var authorId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_author, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = authorViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        arguments?.let {
            authorId = it.getString(AUTHOR_ID).toString()
        }

        authorViewModel.getAuthorById(authorId)

        authorViewModel.currentAuthor.observe(viewLifecycleOwner) { author ->
            (activity as MainActivity).supportActionBar?.title = author.name
        }
    }
}