package com.project.frontMobile.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.project.frontMobile.R

class SearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val bookButton = view.findViewById<MaterialButton>(R.id.book_button)
        val authorButton = view.findViewById<MaterialButton>(R.id.author_button)

        bookButton.setOnClickListener {
            // TODO() : Replace hardcoded string for bookId with id get on recyclerView item
            /*val action = SearchFragmentDirections.actionSearchFragmentToBookFragment("61fbdd5286bac81a8deaacd2")
            view?.findNavController()?.navigate(action)*/
        }

        authorButton.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToAuthorFragment("1")
            view?.findNavController()?.navigate(action)
        }

        return view
    }
}