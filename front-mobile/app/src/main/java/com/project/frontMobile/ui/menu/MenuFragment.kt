package com.project.frontMobile.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.project.frontMobile.R
import com.project.frontMobile.databinding.FragmentMenuBinding
import com.project.frontMobile.utils.RequestStatus
import com.project.frontMobile.utils.SnackbarUtils
import com.project.frontMobile.viewmodel.UserViewModel

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.findMe()

        viewModel.status.observe(viewLifecycleOwner) {
            binding.loading.visibility = View.GONE

            when (it.statusCode) {
                RequestStatus.STATUS_FAIL -> SnackbarUtils().showSnackbar(
                    requireContext(),
                    binding.coordinator,
                    getString(R.string.error_occurred),
                    Snackbar.LENGTH_LONG
                )
            }
        }
    }

    fun onClick(view: View) {
        val action = when (view.id) {
            R.id.account_settings_card -> MenuFragmentDirections.actionMenuFragmentToSettingsFragment()
            R.id.rgpd_card -> MenuFragmentDirections.actionMenuFragmentToRGPDFragment()
            R.id.help_card -> MenuFragmentDirections.actionMenuFragmentToHelpFragment()
            R.id.sign_out_card -> MenuFragmentDirections.actionMenuFragmentToAuthenticationFragment()
            else -> MenuFragmentDirections.actionMenuFragmentToProfileFragment()
        }
        view.findNavController().navigate(action)
    }
}