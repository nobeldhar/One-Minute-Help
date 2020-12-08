package com.decimalab.minutehelp.ui.profile.settings.password

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.decimalab.minutehelp.R
import dagger.android.support.DaggerFragment

class PasswordFragment : DaggerFragment() {

    companion object {
        fun newInstance() = PasswordFragment()
    }

    private lateinit var viewModel: PasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_password, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}