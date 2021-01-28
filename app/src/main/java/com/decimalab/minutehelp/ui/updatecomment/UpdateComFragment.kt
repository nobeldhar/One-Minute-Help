package com.decimalab.minutehelp.ui.updatecomment

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
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentUpdateComBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.profile.timeline.TimelineViewModel
import com.decimalab.minutehelp.ui.reply.ReplyFragment
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UpdateComFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: UpdateComViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentUpdateComBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_com, container, false)
        val isMain = arguments?.let { UpdateComFragmentArgs.fromBundle(it).isMain }
        if (isMain == true){
            val comment = arguments?.let { UpdateComFragmentArgs.fromBundle(it).myComment }
            viewModel.commentId = comment?.id
            viewModel.name = comment?.user?.name
            viewModel.image = comment?.user?.info?.image
            viewModel.text = comment?.comment
            viewModel.isMain = true
        } else{
            val comment = arguments?.let { UpdateComFragmentArgs.fromBundle(it).myChild }
            viewModel.commentId = comment?.id
            viewModel.name = comment?.user?.name
            viewModel.image = comment?.user?.info?.image
            viewModel.text = comment?.comment
            viewModel.isMain = false
        }
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUpdateResult.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response?.status == true){
                        Toast.makeText(requireContext(), "Updated", Toast.LENGTH_LONG).show()
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

        viewModel.getDeleteResult.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response?.status == true){
                        Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_LONG).show()
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
        binding.pbUpdateCom.visibility = visibility
    }

    companion object {
        private const val TAG = "UpdateComFragment"
    }


}