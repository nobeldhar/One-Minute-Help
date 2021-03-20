package com.decimalab.minutehelp.ui.groupdetails.settings.groupaddress

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.groupdetails.GroupDetailsViewModel
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import javax.inject.Inject

class GroupAddreeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel by viewModels<GroupAddreeViewModel> { viewModelFactory }
    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_group_addree, container, false)
    }



}