package com.decimalab.minutehelp.ui.groupdetails.members

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentGroupMembersBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.groupdetails.timeline.GroupTimelineFragment
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GroupMembersFragment(val groupId: Int) : DaggerFragment(),
    SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel by viewModels<GroupMembersViewModel> { viewModelFactory }
    private lateinit var binding: FragmentGroupMembersBinding

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_members, container, false)
        binding.membersSwipe.setOnRefreshListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMembers()
    }

    private fun getMembers() {
        groupId.let { it1 ->
            viewModel.getMembers(it1).observe(viewLifecycleOwner, androidx.lifecycle.Observer {

                binding.membersSwipe.isRefreshing = false
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        val list = it.data
                        binding.memberRecycler.adapter = it.data?.data?.let { it2 ->
                            GrpMembersAdapter(
                                it2.users)
                        }
                        progressVisibility(View.GONE)
                    }
                    Resource.Status.ERROR -> {
                        progressVisibility(View.GONE)
                        Log.d(Companion.TAG, "onActivityCreated: networkError " + it.isNetworkError)
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
    }

    private fun progressVisibility(visibility: Int) {
        binding.pbGrpMember.visibility = visibility
    }

    companion object {
        private const val TAG = "GroupMembersFragment"
    }

    override fun onRefresh() {
        getMembers()
    }


}