package com.decimalab.minutehelp.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.decimalab.minutehelp.DataBinderMapperImpl
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentRegisterBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.login.LoginFragment
import com.decimalab.minutehelp.ui.login.LoginFragmentDirections
import com.decimalab.minutehelp.ui.login.LoginViewModel
import com.decimalab.minutehelp.utils.Resource
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RegisterFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel : RegisterViewModel by viewModels<RegisterViewModel> { viewModelFactory }
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRegisterResult.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        if (response.code == 201 && response.status) {
                            Toast.makeText(requireContext(), response.messages[0], Toast.LENGTH_SHORT).show()
                            val action = RegisterFragmentDirections.actionNavRegisterToNavVerifyCode()
                            findNavController().navigate(action)
                        } else {
                            val message = response.messages.toString()
                            Log.d(TAG, "onViewCreated: failed $message")
                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    progressVisibility(View.GONE)
                    Log.d(TAG, "onActivityCreated: error " + it.isNetworkError)
                    Toast.makeText(requireContext(), it.isNetworkError.toString(), Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING ->
                    progressVisibility(View.VISIBLE)

            }
        })
    }

    private fun progressVisibility(visibility: Int) {
        binding.pbRegister.visibility = visibility
    }

    companion object {
        private const val TAG = "RegisterFragment"
    }

}