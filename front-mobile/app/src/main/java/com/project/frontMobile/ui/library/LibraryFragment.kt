package com.project.frontMobile.ui.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.project.frontMobile.R
import com.project.frontMobile.viewmodel.BookViewModel

class LibraryFragment : Fragment() {

    private val viewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        val bookButton = view.findViewById<MaterialButton>(R.id.book_button)

        bookButton.setOnClickListener {
            // TODO() : Replace hardcoded string for bookId with id get on recyclerView item
            val action = LibraryFragmentDirections.actionLibraryFragmentToBookFragment("6200ea111010dc1e695a2746")
            view?.findNavController()?.navigate(action)
        }

        return view
    }
}