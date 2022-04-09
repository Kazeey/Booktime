package com.project.frontMobile.ui.authentication.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.project.frontMobile.R
import com.project.frontMobile.data.model.User
import com.project.frontMobile.databinding.FragmentSignUpBinding
import com.project.frontMobile.viewmodel.AuthenticationViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private val viewModel: AuthenticationViewModel by activityViewModels()

    private lateinit var user: User

    private var email: String = ""
    private var confirmEmail: String = ""
    private var password: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.currentUser.observe(viewLifecycleOwner) {
            user = it
        }

        viewModel.status.observe(viewLifecycleOwner) {
            binding.loading.visibility = View.GONE
            when (it) {
                "OK" -> {
                    val action = SignUpFragmentDirections.actionSignUpFragmentToCreateProfileFragment(user.id)
                    view.findNavController().navigate(action)
                }
                else -> {
                    val snackbar = Snackbar.make(binding.coordinator, R.string.error_sign_up, Snackbar.LENGTH_LONG)
                    snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.red))
                    snackbar.show()
                }
            }
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.sign_up_button -> {
                if (checkInputs()) {
                    viewModel.signUp(email, password)
                    binding.loading.visibility = View.VISIBLE
                }
                else displayError()
            }
            R.id.back_nav_sign_up -> {
                val action = SignUpFragmentDirections.actionSignUpFragmentToAuthenticationFragment()
                view.findNavController().navigate(action)
            }
        }
    }

    fun onEmailChanged(input: CharSequence) {
        email = input.trim().toString()
        if (binding.emailContainer.error != null) binding.emailContainer.error = null
    }

    fun onConfirmEmailChanged(input: CharSequence) {
        confirmEmail = input.trim().toString()
        if (binding.confirmEmailContainer.error != null) binding.confirmEmailContainer.error = null
    }

    fun onPasswordChanged(input: CharSequence) {
        password = input.trim().toString()
        if (binding.passwordContainer.error != null) binding.passwordContainer.error = null
    }

    private fun displayError() {
        if (email.isBlank()) binding.emailContainer.error = getString(R.string.error_empty_field)
        else if (!isEmail(email)) binding.emailContainer.error = getString(R.string.error_not_email)

        if (confirmEmail.isBlank()) binding.confirmEmailContainer.error = getString(R.string.error_empty_field)
        else if (!isEmailsTheSame()) binding.confirmEmailContainer.error = getString(R.string.error_email_not_match)

        if (password.isBlank()) binding.passwordContainer.error = getString(R.string.error_empty_field)
        else if (!isPassword(password)) binding.passwordContainer.error = getString(R.string.error_not_password)
    }

    private fun checkInputs(): Boolean {
        return isEmail(email) &&
                isEmail(confirmEmail) &&
                isEmailsTheSame() &&
                isPassword(password)
    }

    private fun isEmail(input: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }

    private fun isPassword(input: String): Boolean {
        val regex = Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$")
        return regex.matches(input)
    }

    private fun isEmailsTheSame(): Boolean {
        return email == confirmEmail
    }
}