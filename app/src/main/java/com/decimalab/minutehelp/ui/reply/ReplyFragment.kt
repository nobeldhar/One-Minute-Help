package com.decimalab.minutehelp.ui.reply

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentReplyBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ReplyFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: ReplyViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentReplyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reply, container, false)
        viewModel.postId = arguments?.let { ReplyFragmentArgs.fromBundle(it).postId }
        val isMain = arguments?.let { ReplyFragmentArgs.fromBundle(it).isMain }
        if (isMain == true){
            val comment = arguments?.let { ReplyFragmentArgs.fromBundle(it).myComment }
            viewModel.name = comment?.user?.name
            viewModel.text = comment?.comment
            viewModel.image = comment?.user?.info?.image
            viewModel.comment = comment
            viewModel.reply = "@${comment?.user?.name} "
            viewModel.isMain = true
            Log.d(Companion.TAG, "onCreateView: ${comment?.user?.name}")
        } else{
            val child = arguments?.let { ReplyFragmentArgs.fromBundle(it).myChild }
            viewModel.name = child?.user?.name
            viewModel.text = child?.comment
            viewModel.image = child?.user?.info?.image
            viewModel.comment = arguments?.let { ReplyFragmentArgs.fromBundle(it).myComment }
            viewModel.child = child
            viewModel.reply = "@${child?.user?.name} "
            viewModel.isMain = false
            Log.d(Companion.TAG, "onCreateView: ${child?.user?.name}")
        }
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getReplyResult.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response?.status == true){
                        Toast.makeText(requireContext(), "Replied", Toast.LENGTH_LONG).show()
                        requireActivity().onBackPressed()
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
        binding.pbReply.visibility = visibility
    }

    companion object {
        private const val TAG = "ReplyFragment"
    }


}