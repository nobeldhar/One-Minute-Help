package com.decimalab.minutehelp.ui.profile.settings.basic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.decimalab.minutehelp.R
import dagger.android.support.DaggerFragment

class BasicFragment : DaggerFragment() {

    companion object {
        fun newInstance() = BasicFragment()
    }

    private lateinit var viewModel: BasicViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_basic, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BasicViewModel::class.java)
        // TODO: Use the ViewModel
    }

}