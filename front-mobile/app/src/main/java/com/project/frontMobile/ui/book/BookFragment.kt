package com.project.frontMobile.ui.book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.project.frontMobile.R

class BookFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book, container, false)

        val authorButton = view.findViewById<MaterialButton>(R.id.author_button)

        authorButton.setOnClickListener {
            val action = BookFragmentDirections.actionBookFragmentToAuthorFragment()
            view?.findNavController()?.navigate(action)
        }

        return view
    }
}