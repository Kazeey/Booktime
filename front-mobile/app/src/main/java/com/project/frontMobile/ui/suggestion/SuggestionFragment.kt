package com.project.frontMobile.ui.suggestion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.project.frontMobile.R

class SuggestionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_suggestion, container, false)

        val searchButton = view.findViewById<MaterialButton>(R.id.search_button)
        val bookButton = view.findViewById<MaterialButton>(R.id.book_button)

        bookButton.setOnClickListener {
            val action = SuggestionFragmentDirections.actionSuggestionFragmentToBookFragment()
            view?.findNavController()?.navigate(action)
        }

        searchButton.setOnClickListener {
            val action = SuggestionFragmentDirections.actionSuggestionFragmentToSearchFragment()
            view?.findNavController()?.navigate(action)
        }

        return view
    }
}