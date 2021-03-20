package com.decimalab.minutehelp.ui.groupdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentGroupDetailsBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.comments.CommentsFragmentArgs
import com.decimalab.minutehelp.ui.dashboard.DashboardViewModel
import com.decimalab.minutehelp.ui.groupdetails.groupcreatepost.GroupCreatePostFragment
import com.decimalab.minutehelp.ui.profile.ProfileFragment
import com.decimalab.minutehelp.ui.profile.ProfilePagerAdapter
import com.decimalab.minutehelp.ui.profile.addhistory.AddHistoryFragment
import com.decimalab.minutehelp.ui.profile.createpost.CreatePostFragment
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.ViewUtils
import com.github.drjacky.imagepicker.ImagePicker
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GroupDetailsFragment : DaggerFragment(), View.OnClickListener {

    private var groupId: Int? = null

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel by viewModels<GroupDetailsViewModel> { viewModelFactory }

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper
    private lateinit var binding: FragmentGroupDetailsBinding

    private val configureIsAdmin = TabLayoutMediator.TabConfigurationStrategy { tab, position ->
        when (position) {
            0 -> tab.text = "Posts"
            1 -> tab.text = "Members"
            2 -> tab.text = "Settings"
        }
    }

    private val configureNotAdmin = TabLayoutMediator.TabConfigurationStrategy { tab, position ->
        when (position) {
            0 -> tab.text = "Posts"
            1 -> tab.text = "Members"

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_group_details, container, false)
        groupId = arguments?.let { GroupDetailsFragmentArgs.fromBundle(it) }?.groupId!!
        binding.btGrpCreatePost.setOnClickListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        groupId?.let {
            viewModel.getGroupById(it).observe(viewLifecycleOwner, Observer { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        progressVisibility(View.GONE)
                        val response = resource.data
                        if (response != null) {
                            val response = response.data
                            binding.group = response
                        }
                    }
                    Resource.Status.ERROR -> {
                        progressVisibility(View.GONE)
                        Log.d(TAG, "onViewCreated: networkError " + resource.isNetworkError)
                        resource.isNetworkError?.let { it ->
                            if (it) {
                                ViewUtils.toastNoInternet(requireActivity(), requireContext())
                            }
                        }
                    }
                    Resource.Status.LOADING ->
                        progressVisibility(View.VISIBLE)
                }
            })
        }

        viewModel.getUser().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        response.data.group?.let { group ->
                            if (group.id == groupId) {
                                if (response.data.role.role == "Admin") {
                                    initViewPagerWithSettings()
                                }
                                binding.btGrpCreatePost.visibility = View.VISIBLE
                                binding.btAddMember.visibility = View.VISIBLE
                                initViewPagerWithoutSettings()
                            } else {
                                initViewPagerWithoutSettings()
                            }
                        }

                    }
                }
                Resource.Status.ERROR -> {
                    progressVisibility(View.GONE)
                    Log.d(TAG, "onViewCreated: networkError " + it.isNetworkError)
                    it.isNetworkError?.let { it ->
                        if (it) {
                            ViewUtils.toastNoInternet(requireActivity(), requireContext())
                        }
                    }
                }
                Resource.Status.LOADING ->
                    progressVisibility(View.VISIBLE)
            }
        })


    }

    private fun initViewPagerWithoutSettings() {
        binding.groupViewPager.adapter = groupId?.let { GroupPagerAdapter(this, it) }
        val tabLayoutMediator = TabLayoutMediator(
            binding.profileTab, binding.groupViewPager, configureNotAdmin
        )
        tabLayoutMediator.attach()
    }

    private fun initViewPagerWithSettings() {
        binding.groupViewPager.adapter = groupId?.let { GroupPagerAdapter(this, it, true) }
        val tabLayoutMediator = TabLayoutMediator(
            binding.profileTab, binding.groupViewPager, configureIsAdmin
        )
        tabLayoutMediator.attach()
    }

    private fun progressVisibility(visibility: Int) {
        binding.pbGrpDetails.visibility = visibility
    }

    companion object {
        private const val TAG = "GroupDetailsFragment"
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btGrpCreatePost -> {
                val bottomFragment = groupId?.let { GroupCreatePostFragment(it) }
                bottomFragment?.show(parentFragmentManager, TAG)
            }

        }
    }


}