package com.project.frontMobile.ui.loading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.project.frontMobile.R
import com.project.frontMobile.utils.RequestStatus
import com.project.frontMobile.viewmodel.UserViewModel

class LoadingFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.findMe()

        viewModel.status.observe(viewLifecycleOwner) {
            val action = when (it.statusCode) {
                RequestStatus.STATUS_OK -> LoadingFragmentDirections.actionLoadingFragmentToLibraryFragment()
                else -> LoadingFragmentDirections.actionLoadingFragmentToAuthenticationFragment()
            }
            view.findNavController().navigate(action)
        }
    }
}