package com.project.frontMobile.ui.authentication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.project.frontMobile.R

class LogInFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_log_in, container, false)

        val signInButton = view.findViewById<MaterialButton>(R.id.log_in_button)
        val forgotPasswordButton = view.findViewById<MaterialButton>(R.id.forgot_password_button)

        signInButton.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToLibraryFragment()
            view?.findNavController()?.navigate(action)
        }

        forgotPasswordButton.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToForgotPasswordFragment()
            view?.findNavController()?.navigate(action)
        }

        return view
    }
}