package com.project.frontMobile.view.authentication.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.project.frontMobile.R

class SignUpFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val signUpButton = view.findViewById<MaterialButton>(R.id.sign_up_button)

        signUpButton.setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToLibraryFragment()
            view?.findNavController()?.navigate(action)
        }

        return view
    }
}