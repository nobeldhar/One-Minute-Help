package com.decimalab.minutehelp.ui.profile.timeline

import android.media.MediaPlayer
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
import com.decimalab.minutehelp.data.local.entities.Post
import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.decimalab.minutehelp.databinding.FragmentTimelineBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.dashboard.allposts.AllPostsFragment
import com.decimalab.minutehelp.ui.dashboard.allposts.PostAdapter
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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TimelineFragment : DaggerFragment(), SwipeRefreshLayout.OnRefreshListener,
    CustomOnClickListener {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: TimelineViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var prefsHelper: SharedPrefsHelper
    private lateinit var binding: FragmentTimelineBinding
    private var userId: Int? = null
    private var media : MediaPlayer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false)
        userId = prefsHelper.getUser()?.id
        media = MediaPlayer.create(requireContext(), R.raw.like)
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
                    bindAdapter(list)
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

    private fun bindAdapter(list: List<TimeLinePost>?) {
        list?.let {
            if (list.isNotEmpty()){
                for (post in it){
                    //val parser = SimpleDateFormat.getDateTimeInstance().format("yyyy-MM-dd'T'HH:mm:ss")
                    try{
                        val parser = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss", Locale.US)
                        val formatter = SimpleDateFormat("EEE, MMM d, 'at' hh:mm aaa", Locale.US)
                        val output =
                            formatter.format(parser.parse(post.createdAt.replace("T", " ")))
                        post.createdAt = output
                    } catch (e: Exception){
                        Log.d(TAG, "bindAdapter: ${e.message}")
                    }

                    if (post.likes.isNotEmpty()){
                        for (like in post.likes){
                            if (like.userId == userId)
                                post.isLiked = true
                        }
                    }
                }
                binding.tmRecycler.adapter =TimelinePostAdapter(it, this)
                progressVisibility(View.GONE)
            }
        }
    }

    companion object {
        private const val TAG = "TimelineFragment"
    }

    override fun onRefresh() {
        getPosts()
        getPosts()
    }

    override fun onTMCommentsClicked(timeLinePost: TimeLinePost) {
        val action = ProfileFragmentDirections.actionNavProfileToNavComments(timeLinePost.id)
        findNavController().navigate(action)
    }

    override fun onTMInterestedClicked(timeLinePost: TimeLinePost) {
        media?.start()
        likePost(timeLinePost)
        Log.d(TAG, "onInterestedClicked: Clicked")

    }

    private fun likePost(post: TimeLinePost) {
        viewModel.likePost(post).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data?.status == true){
                        Toast.makeText(requireContext(), it.data.messages.toString(), Toast.LENGTH_SHORT).show()
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

    override fun onDestroy() {
        super.onDestroy()
        media?.stop()
        media?.release()
    }

}



