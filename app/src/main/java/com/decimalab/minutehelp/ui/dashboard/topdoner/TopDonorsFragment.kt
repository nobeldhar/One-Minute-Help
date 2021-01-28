package com.decimalab.minutehelp.ui.dashboard.topdoner

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.dashboard.allposts.AllPostsViewModel
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TopDonorsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel : TopDonorsViewModel by viewModels{ viewModelFactory }

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_donors, container, false)
    }

}