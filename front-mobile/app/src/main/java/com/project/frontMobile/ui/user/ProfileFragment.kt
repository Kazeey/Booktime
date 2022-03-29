package com.project.frontMobile.ui.user

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.button.MaterialButton
import com.project.frontMobile.R
import com.project.frontMobile.data.model.User
import com.project.frontMobile.databinding.FragmentProfileBinding
import com.project.frontMobile.utils.ClickHandler
import com.project.frontMobile.utils.DateUtils
import com.project.frontMobile.viewmodel.UserViewModel

class ProfileFragment : Fragment(), ClickHandler {

    private lateinit var binding: FragmentProfileBinding

    private val userViewModel: UserViewModel by activityViewModels()
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

        binding.viewModel = userViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.handler = this

        userViewModel.currentUser.observe(viewLifecycleOwner) {
            currentUser = it
            binding.birthday.text = DateUtils().formatBirthday(currentUser.birthday)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            binding.usernameContainer.id -> {
                openDialog()
            }
            binding.birthdayContainer.id  -> {
                openDatePicker(
                    Integer.parseInt(DateUtils().extractYear(currentUser.birthday)),
                    Integer.parseInt(DateUtils().extractMonth(currentUser.birthday)) - 1,
                    Integer.parseInt(DateUtils().extractDayOfMonth(currentUser.birthday))
                )
            }
            else -> Log.i(ProfileFragment::class.java.name, "Unknown view")
        }
    }

    private fun openDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.custom_dialog, null)
        val alertDialog = builder.create()
        alertDialog.setView(dialogLayout)

        val editText = dialogLayout.findViewById<EditText>(R.id.username)
        editText.setText(currentUser.pseudo)
        val positiveBtn = dialogLayout.findViewById<MaterialButton>(R.id.positive)
        val negativeBtn = dialogLayout.findViewById<MaterialButton>(R.id.negative)

        positiveBtn.setOnClickListener {
            currentUser.pseudo = editText.text.toString().trim()
            userViewModel.updateUser(currentUser)
            alertDialog.dismiss()
        }

        negativeBtn.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun openDatePicker(year: Int, month: Int, day: Int) {
        DatePickerDialog(requireContext(), { _, y, m, d ->
            currentUser.birthday = DateUtils().formatDate(y, m, d)
            userViewModel.updateUser(currentUser)
        }, year, month, day)
            .show()
    }
}