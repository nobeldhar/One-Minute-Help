package com.decimalab.minutehelp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentLoginBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
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
        return binding.root
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
}