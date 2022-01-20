package com.project.frontMobile.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.project.frontMobile.R

class AuthenticationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_authentication, container, false)

        val logIn: MaterialButton = view.findViewById(R.id.log_in_button)
        val signUp: MaterialButton = view.findViewById(R.id.sign_up_button)

        logIn.setOnClickListener {
            val action = AuthenticationFragmentDirections.actionAuthenticationFragmentToLogInFragment()
            view?.findNavController()?.navigate(action)
        }

        signUp.setOnClickListener {
            val action = AuthenticationFragmentDirections.actionAuthenticationFragmentToSignUpFragment()
            view?.findNavController()?.navigate(action)
        }

        return view
    }
}