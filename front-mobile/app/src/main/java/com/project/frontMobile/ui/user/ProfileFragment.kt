package com.project.frontMobile.ui.user

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.project.frontMobile.R
import com.project.frontMobile.data.model.User
import com.project.frontMobile.databinding.FragmentProfileBinding
import com.project.frontMobile.utils.DateUtils
import com.project.frontMobile.utils.RequestCode
import com.project.frontMobile.utils.RequestStatus
import com.project.frontMobile.utils.SnackbarUtils
import com.project.frontMobile.viewmodel.UserViewModel
import java.util.*

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: UserViewModel by viewModels()

    private lateinit var currentUser: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner

        configureToolbar()

        viewModel.findMe()

        viewModel.currentUser.observe(viewLifecycleOwner) {
            currentUser = it
            binding.birthday.text = DateUtils().formatBirthday(currentUser.birthday)
        }

        viewModel.status.observe(viewLifecycleOwner) {
            binding.loading.visibility = View.GONE

            when (it.statusCode) {
                RequestStatus.STATUS_OK -> {
                    if (it.requestCode == RequestCode.REQUEST_CODE_UPDATE_USER) {
                        requireActivity().onBackPressed()
                    }
                }
                else -> SnackbarUtils().showSnackbar(
                    requireContext(),
                    binding.coordinator,
                    getString(R.string.error_occurred),
                    Snackbar.LENGTH_LONG
                )
            }
        }

        binding.birthdayContainer.setOnClickListener {
            openDatePicker()
        }
    }

    private fun configureToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.save -> {
                    if (checkInputs()) {
                        openDialog()
                    } else displayError()
                }

            }
            true
        }
    }

    private fun openDatePicker() {
        val c = Calendar.getInstance()

        DatePickerDialog(requireContext(), { _, y, m, d ->
            currentUser.birthday = DateUtils().formatDate(y, m, d)
            binding.birthday.text = DateUtils().formatBirthday(currentUser.birthday)
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

    private fun openDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.title_save_data)
            .setPositiveButton(R.string.button_save) { _, _ ->
                binding.loading.visibility = View.VISIBLE
                viewModel.updateUser(currentUser)
            }
            .setNegativeButton(R.string.button_discard) { _, _ -> }
            .show()
    }
}