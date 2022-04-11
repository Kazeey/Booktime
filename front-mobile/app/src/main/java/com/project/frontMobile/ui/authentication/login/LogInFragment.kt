package com.project.frontMobile.ui.authentication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.project.frontMobile.R
import com.project.frontMobile.data.model.User
import com.project.frontMobile.databinding.FragmentLogInBinding
import com.project.frontMobile.viewmodel.AuthenticationViewModel

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    private val viewModel: AuthenticationViewModel by viewModels()

    private lateinit var user: User

    private var email: String = ""
    private var password: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false)

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
                    viewModel.clearStatus()
                    val action = LogInFragmentDirections.actionLogInFragmentToLibraryFragment()
                    view.findNavController().navigate(action)
                }
                "NOT_FOUND" -> showSnackbar(getString(R.string.error_wrong_ids), Snackbar.LENGTH_INDEFINITE)
                "FAIL" -> showSnackbar(getString(R.string.error_log_in), Snackbar.LENGTH_LONG)
            }
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.log_in_button -> {
                if (checkInputs()) {
                    viewModel.logIn(email, password)
                    binding.loading.visibility = View.VISIBLE
                } else displayError()
            }
            R.id.forgot_password_button -> {
                val action = LogInFragmentDirections.actionLogInFragmentToForgotPasswordFragment()
                view.findNavController().navigate(action)
            }
            R.id.back_nav_log_in -> {
                val action = LogInFragmentDirections.actionLogInFragmentToAuthenticationFragment()
                view.findNavController().navigate(action)
            }
        }
    }

    fun onEmailChanged(input: CharSequence) {
        email = input.trim().toString()
        if (binding.emailContainer.error != null) binding.emailContainer.error = null
    }

    fun onPasswordChanged(input: CharSequence) {
        password = input.trim().toString()
        if (binding.passwordContainer.error != null) binding.passwordContainer.error = null
    }

    private fun displayError() {
        if (email.isBlank()) binding.emailContainer.error = getString(R.string.error_empty_field)
        else if (!isEmail(email)) binding.emailContainer.error = getString(R.string.error_not_email)

        if (password.isBlank()) binding.passwordContainer.error = getString(R.string.error_empty_field)
        else if (!isPassword(password)) binding.passwordContainer.error = getString(R.string.error_not_password)
    }

    private fun checkInputs(): Boolean {
        return isEmail(email) && isPassword(password)
    }

    private fun isEmail(input: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }

    private fun isPassword(input: String): Boolean {
        val regex = Regex("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$")
        return regex.matches(input)
    }

    private fun showSnackbar(message: String, duration: Int) {
        val snackbar = Snackbar.make(binding.coordinator, message, duration)
        snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.red))
        if (duration == Snackbar.LENGTH_INDEFINITE) {
            snackbar.setActionTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            snackbar.setAction(R.string.button_ok) {
                snackbar.dismiss()
            }
        }
        snackbar.show()
    }
}