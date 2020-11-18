package com.decimalab.minutehelp.ui.verifycode

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.decimalab.minutehelp.R

class VerifyCodeFragment : Fragment() {

    companion object {
        fun newInstance() = VerifyCodeFragment()
    }

    private lateinit var viewModel: VerifyCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verfy_code, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VerifyCodeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}