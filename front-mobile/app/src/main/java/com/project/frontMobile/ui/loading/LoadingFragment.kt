package com.project.frontMobile.ui.loading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.project.frontMobile.R

class LoadingFragment : Fragment() {
    var user: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onResume() {
        super.onResume()

        if (user) {
            val action = LoadingFragmentDirections.actionLoadingFragmentToLibraryFragment()
            view?.findNavController()?.navigate(action)
        } else {
            val action = LoadingFragmentDirections.actionLoadingFragmentToAuthenticationFragment()
            view?.findNavController()?.navigate(action)
        }
    }
}