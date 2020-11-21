package com.decimalab.minutehelp.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentLoginBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val loginViewModel by viewModels<LoginViewModel> { viewModelFactory }
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.fragment = this
        binding.viewModel = loginViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.getLoginResult.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        if (response.data.code == 200 and response.data.status){
                            Toast.makeText(requireContext(), response.data.email, Toast.LENGTH_SHORT).show()
                        } else if(response.data.code == 400){
                            val message = response.messages.toString()
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }

                    }
                }
                Resource.Status.ERROR -> {
                    progressVisibility(View.GONE)
                    Log.d(Companion.TAG, "onViewCreated: " + it.message)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING ->
                    progressVisibility(View.VISIBLE)

            }
        })
    }

    private fun progressVisibility(visible: Int) {
        binding.pbLogin.visibility = visible
    }

    fun onRegisterClicked(){
        val action = LoginFragmentDirections.actionNavLoginToNavRegister()
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        ViewUtils.hideToolbar(requireActivity())
        ViewUtils.hideStatusBar(requireActivity())
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}