package com.project.frontMobile.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.project.frontMobile.R

class MenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val profileButton = view.findViewById<MaterialButton>(R.id.profile_button)
        val settingsButton = view.findViewById<MaterialButton>(R.id.settings_button)
        val rgpdButton = view.findViewById<MaterialButton>(R.id.rgpd_button)
        val helpButton = view.findViewById<MaterialButton>(R.id.help_button)
        val signOutButton = view.findViewById<MaterialButton>(R.id.sign_out_button)

        profileButton.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToProfileFragment()
            view?.findNavController()?.navigate(action)
        }

        settingsButton.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToSettingsFragment()
            view?.findNavController()?.navigate(action)
        }

        rgpdButton.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToRGPDFragment()
            view?.findNavController()?.navigate(action)
        }

        helpButton.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToHelpFragment()
            view?.findNavController()?.navigate(action)
        }

        signOutButton.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToAuthenticationFragment()
            view?.findNavController()?.navigate(action)
        }

        return view
    }
}