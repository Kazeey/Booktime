package com.project.frontMobile.ui.user

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.project.frontMobile.R
import com.project.frontMobile.data.model.User
import com.project.frontMobile.databinding.FragmentCreateProfileBinding
import com.project.frontMobile.utils.DateUtils
import com.project.frontMobile.viewmodel.ProfileViewModel
import java.util.*

class CreateProfileFragment : Fragment() {

    private lateinit var binding: FragmentCreateProfileBinding

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var currentUser: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_profile, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getUserById("6231ce3362c151333c14f83e")

        viewModel.currentUser.observe(viewLifecycleOwner) {
            currentUser = it
            binding.birthday.text = DateUtils().formatBirthday(currentUser.birthday)
        }

        binding.birthdayContainer.setOnClickListener {
            openDatePicker()
        }
    }

    private fun openDatePicker() {
        val c = Calendar.getInstance()

        DatePickerDialog(requireContext(), { _, y, m, d ->
            currentUser.birthday = DateUtils().formatDate(y, m, d)
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
            .show()
    }

    fun onPseudoChanged(input: CharSequence) {
        currentUser.pseudo = input.trim().toString()
        if (binding.pseudoContainer.error != null) binding.pseudoContainer.error = null
    }

    fun onNameChanged(input: CharSequence) {
        currentUser.name = input.trim().toString()
        if (binding.nameContainer.error != null) binding.nameContainer.error = null
    }

    fun onFirstnameChanged(input: CharSequence) {
        currentUser.firstName = input.trim().toString()
        if (binding.firstnameContainer.error != null) binding.firstnameContainer.error = null
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.next_button -> {
                if (checkInputs()) {
                    viewModel.updateUser(currentUser)
                    val action = CreateProfileFragmentDirections.actionCreateProfileFragmentToLibraryFragment()
                    view.findNavController().navigate(action)
                }
                else displayError()
            }
        }
    }

    private fun displayError() {
        if (currentUser.pseudo.isBlank()) binding.pseudoContainer.error = getString(R.string.error_empty_field)
        if (currentUser.name.isBlank()) binding.nameContainer.error = getString(R.string.error_empty_field)
        if (currentUser.firstName.isBlank()) binding.firstnameContainer.error = getString(R.string.error_empty_field)
    }

    private fun checkInputs(): Boolean {
        return currentUser.pseudo.isNotBlank() &&
                currentUser.name.isNotBlank() &&
                currentUser.firstName.isNotBlank() &&
                currentUser.birthday.isNotBlank()
    }
}