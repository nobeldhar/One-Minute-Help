package com.decimalab.minutehelp.ui.profile.settings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentSettingsBinding
import com.decimalab.minutehelp.ui.profile.ProfileFragmentDirections
import com.decimalab.minutehelp.utils.CustomOnClickListener

class SettingsFragment : Fragment(), CustomOnClickListener {

    private val OPTION_BASIC = "Basic"
    private val OPTION_INFORMATION = "Information"
    private val OPTION_ADDRESS = "Address"
    private val OPTION_PASSWORD = "Password"
    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel
    private lateinit var binding : FragmentSettingsBinding
    private val optionsList = listOf<String>(OPTION_BASIC, OPTION_INFORMATION, OPTION_ADDRESS, OPTION_PASSWORD)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        binding.settingsRecycler.adapter = SettingsOptionAdapter(optionsList, this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onSettingsOptionClicked(option: String) {
        when(option){
            OPTION_BASIC->findNavController().navigate(ProfileFragmentDirections.actionNavProfileToNavBasicSettings())
            OPTION_ADDRESS->findNavController().navigate(ProfileFragmentDirections.actionNavProfileToNavAddressSetting())
            OPTION_INFORMATION->findNavController().navigate(ProfileFragmentDirections.actionNavProfileToNavInfoSettings())
            OPTION_PASSWORD->findNavController().navigate(ProfileFragmentDirections.actionNavProfileToNavPassSetting())

        }
    }


}