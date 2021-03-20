package com.decimalab.minutehelp.ui.groupdetails.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentGroupSettingsBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.groupdetails.GroupDetailsFragmentDirections
import com.decimalab.minutehelp.ui.profile.settings.SettingsOptionAdapter
import com.decimalab.minutehelp.utils.CustomOnClickListener
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GroupSettingsFragment(val groupId: Int) : DaggerFragment(), CustomOnClickListener {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel by viewModels<GroupSettingsViewModel> { viewModelFactory }

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper

    private lateinit var binding: FragmentGroupSettingsBinding
    private val OPTION_BASIC = "Basic"
    private val OPTION_INFORMATION = "Information"
    private val OPTION_ADDRESS = "Address"
    private val OPTION_PERMISSION = "Permission"
    private val optionsList = listOf<String>(OPTION_BASIC, OPTION_INFORMATION, OPTION_ADDRESS, OPTION_PERMISSION)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_members, container, false)
        binding.groupSettingRecycler.adapter = SettingsOptionAdapter(optionsList, this)
        return binding.root
    }

    override fun onSettingsOptionClicked(option: String) {
        when(option){
            OPTION_ADDRESS-> findNavController().navigate(GroupDetailsFragmentDirections.actionNavGroupDetailsToGroupAddreeFragment())
            OPTION_INFORMATION-> findNavController().navigate(GroupDetailsFragmentDirections.actionNavGroupDetailsToGroupInfoFragment())
            OPTION_BASIC-> findNavController().navigate(GroupDetailsFragmentDirections.actionNavGroupDetailsToGroupBasicFragment())
            OPTION_PERMISSION-> findNavController().navigate(GroupDetailsFragmentDirections.actionNavGroupDetailsToGroupPermissionFragment())
        }
    }



}