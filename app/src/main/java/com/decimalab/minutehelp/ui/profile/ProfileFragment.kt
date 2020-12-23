package com.decimalab.minutehelp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentProfileBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.profile.addhistory.AddHistoryFragment
import com.decimalab.minutehelp.ui.profile.createpost.CreatePostFragment
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment(), View.OnClickListener {

    @Inject
    lateinit var prefsHelper: SharedPrefsHelper
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentProfileBinding
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
        binding.profileName.text = prefsHelper.getUser()?.name
        binding.profilePic.load(R.drawable.pro_pic_placeholder){
            transformations(CircleCropTransformation())
        }
        binding.btCreatePost.setOnClickListener(this)
        binding.btAddDonateHistory.setOnClickListener(this)
        tabLayoutMediator.attach()
        return binding.root
    }

    override fun onClick(v: View?) {
        when(v){
            binding.btCreatePost->{
                val bottomFragment = CreatePostFragment()
                bottomFragment.show(parentFragmentManager, TAG )
            }
            binding.btAddDonateHistory->{
                val bottomFragment = AddHistoryFragment()
                bottomFragment.show(parentFragmentManager, TAG)
            }
        }
    }

    companion object {
        private const val TAG = "ProfileFragment"
    }


}