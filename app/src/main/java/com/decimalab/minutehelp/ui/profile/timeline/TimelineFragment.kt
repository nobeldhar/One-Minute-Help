package com.decimalab.minutehelp.ui.profile.timeline

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.decimalab.minutehelp.databinding.FragmentTimelineBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.profile.ProfileFragmentDirections
import com.decimalab.minutehelp.ui.profile.settings.address.AddressViewModel
import com.decimalab.minutehelp.ui.profile.settings.information.InformationFragment
import com.decimalab.minutehelp.utils.CustomOnClickListener
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerFragment
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class TimelineFragment : DaggerFragment(), SwipeRefreshLayout.OnRefreshListener,
    CustomOnClickListener {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: TimelineViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var prefsHelper: SharedPrefsHelper
    private lateinit var binding: FragmentTimelineBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false)
        binding.timelineSwipe.setOnRefreshListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPosts()
    }

    private fun getPosts() {
        viewModel.getTimeLinePosts().observe(viewLifecycleOwner, Observer {
            binding.timelineSwipe.isRefreshing = false
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val list = it.data
                    Log.d(TAG, "onViewCreated: adapter set")
                    binding.tmRecycler.adapter = list?.let { it1 -> TimelinePostAdapter(it1, this) }
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

    private fun progressVisibility(visibility: Int) {
        binding.pbTimeline.visibility = visibility
    }

    companion object {
        private const val TAG = "TimelineFragment"
    }

    override fun onRefresh() {
        getPosts()
        getPosts()
    }

    override fun onCommentsClicked(timeLinePost: TimeLinePost) {
        val action = ProfileFragmentDirections.actionNavProfileToNavComments(timeLinePost.id)
        findNavController().navigate(action)
    }

    override fun onInterestedClicked(timeLinePost: TimeLinePost) {
        Toast.makeText(requireContext(), "Liked : ${timeLinePost.id}", Toast.LENGTH_SHORT).show()

    }

}



