package com.decimalab.minutehelp.ui.profile

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentProfileBinding
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private val configure = TabLayoutMediator.TabConfigurationStrategy { tab, position ->
        when(position){
            0->tab.text = "Timeline"
            1-> tab.text = "Donate History"
            2-> tab.text = "Group"
            3-> tab.text = "Settings"
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.profileViewPager.adapter = ProfilePagerAdapter(this)
        val tabLayoutMediator = TabLayoutMediator(
            binding.profileTab, binding.profileViewPager, configure
        )
        tabLayoutMediator.attach()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}