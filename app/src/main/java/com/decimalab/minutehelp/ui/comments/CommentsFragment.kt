package com.decimalab.minutehelp.ui.comments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.local.entities.Comment
import com.decimalab.minutehelp.databinding.FragmentCommentsBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.profile.timeline.TimelineFragment
import com.decimalab.minutehelp.ui.profile.timeline.TimelinePostAdapter
import com.decimalab.minutehelp.ui.reply.ReplyViewModel
import com.decimalab.minutehelp.ui.updatecomment.UpdateComViewModel
import com.decimalab.minutehelp.utils.CustomOnClickListener
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.math.log

class CommentsFragment : DaggerFragment(), View.OnClickListener, CustomOnClickListener {
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: CommentsViewModel by viewModels { viewModelFactory }
    private val replyViewModel: ReplyViewModel by viewModels { viewModelFactory }
    private val updateComViewModel: UpdateComViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentCommentsBinding
    private var postId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comments, container, false)
        binding.viewModel = viewModel
        binding.btComment.setOnClickListener(this)
        postId = arguments?.let { CommentsFragmentArgs.fromBundle(it) }?.postId!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getComments()
    }

    private fun getComments() {
        postId.let { viewModel.getComments(it) }?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response?.status == true) {
                        if (response.comments.isNullOrEmpty())
                            Log.d(TAG, "getComments: empty")
                        binding.commentRecycler.adapter = CommentsAdapter(response.comments, this)
                    }
                    Log.d(TAG, "commentOnPost: comment failed")
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

    override fun onClick(v: View?) {
        when(v){
            binding.btComment -> {
                commentOnPost()
                Log.d(TAG, "onClick: commentClicked")
            }
        }
    }

    private fun commentOnPost() {
        postId?.let {
            Log.d(TAG, "commentOnPost: postId not null")
            viewModel.commentOnPost(it) }?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {

                    val response = it.data
                    if (response?.status == true) {
                        getComments()
                    } else
                        progressVisibility(View.GONE)
                    Log.d(TAG, "commentOnPost: comment failed")
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
        binding.pbComment.visibility = visibility
    }

    companion object {
        private const val TAG = "CommentsFragment"
    }

    override fun onMainCommentReply(comment: Comment) {
        findNavController().navigate(CommentsFragmentDirections.actionNavCommentsToNavReply(true, comment, postId = postId))
    }

    override fun onChildCommentReply(comment: Comment, child: Comment.Child) {
        findNavController().navigate(CommentsFragmentDirections.actionNavCommentsToNavReply(false, comment, child, postId))
    }
}