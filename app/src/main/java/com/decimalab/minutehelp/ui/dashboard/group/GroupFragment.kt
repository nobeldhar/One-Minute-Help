package com.decimalab.minutehelp.ui.dashboard.group

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
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.local.entities.Group
import com.decimalab.minutehelp.databinding.FragmentGroupBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.dashboard.DashboardFragmentDirections
import com.decimalab.minutehelp.ui.dashboard.allposts.AllPostsFragment
import com.decimalab.minutehelp.ui.dashboard.members.MembersViewModel
import com.decimalab.minutehelp.utils.CustomOnClickListener
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GroupFragment : DaggerFragment(), SwipeRefreshLayout.OnRefreshListener,
    CustomOnClickListener {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel : MembersViewModel by viewModels{ viewModelFactory }

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper
    private lateinit var binding: FragmentGroupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group, container, false)
        binding.groupsSwipe.setOnRefreshListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getGroups()
    }

    override fun onRefresh() {
        getGroups()
    }

    private fun getGroups() {
        viewModel.getGroups().observe(viewLifecycleOwner, Observer {
            binding.groupsSwipe.isRefreshing = false
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val list = it.data
                    if (list?.isNotEmpty() == true){
                        binding.groupsRecycler.adapter = GroupsAdapter(list, this)
                    }
                }
                Resource.Status.ERROR -> {
                    progressVisibility(View.GONE)
                    Log.d(TAG, "onActivityCreated: networkError " + it.isNetworkError)
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

    private fun progressVisibility(visibility: Int) {
        binding.pbGroups.visibility = visibility
    }
    companion object {
        private const val TAG = "GroupFragment"
    }

    override fun onGroupClicked(group: Group) {
        findNavController().navigate(DashboardFragmentDirections.actionNavDashboardToNavGroupDetails(group.id))
    }

}