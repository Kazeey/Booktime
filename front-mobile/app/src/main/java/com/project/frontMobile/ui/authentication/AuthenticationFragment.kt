package com.project.frontMobile.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.project.frontMobile.R
import com.project.frontMobile.databinding.FragmentAuthenticationBinding
import com.project.frontMobile.utils.ClickHandler

class AuthenticationFragment : Fragment(), ClickHandler {

    private lateinit var binding: FragmentAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_authentication, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.handler = this
        binding.lifecycleOwner = viewLifecycleOwner

       // requireActivity().onBackPressedDispatcher.addCallback { }
    }

    override fun onClick(view: View) {
        val action = when (view.id) {
            R.id.log_in_button -> AuthenticationFragmentDirections.actionAuthenticationFragmentToLogInFragment()
            else -> AuthenticationFragmentDirections.actionAuthenticationFragmentToSignUpFragment()
        }
        view.findNavController().navigate(action)
    }
}