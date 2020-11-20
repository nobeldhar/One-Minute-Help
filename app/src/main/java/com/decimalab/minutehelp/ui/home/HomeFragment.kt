package com.decimalab.minutehelp.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentHomeBinding
import com.decimalab.minutehelp.databinding.FragmentLoginBinding
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.name.text = sharedPrefsHelper.fetchAuthToken()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}