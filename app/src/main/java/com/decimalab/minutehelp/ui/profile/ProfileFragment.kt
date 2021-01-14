package com.decimalab.minutehelp.ui.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.load
import coil.transform.CircleCropTransformation
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.databinding.FragmentProfileBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.profile.addhistory.AddHistoryFragment
import com.decimalab.minutehelp.ui.profile.createpost.CreatePostFragment
import com.decimalab.minutehelp.ui.profile.settings.address.AddressFragment
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.ViewUtils
import com.github.drjacky.imagepicker.ImagePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import java.io.File
import javax.inject.Inject

class ProfileFragment : DaggerFragment(), View.OnClickListener {

    private val UPDATE_IMAGE_REQUEST_CODE: Int = 2334

    @Inject
    lateinit var prefsHelper: SharedPrefsHelper

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentProfileBinding
    private var file: File? = null
    private val configure = TabLayoutMediator.TabConfigurationStrategy { tab, position ->
        when (position) {
            0 -> tab.text = "Timeline"
            1 -> tab.text = "Donate History"
            2 -> tab.text = "Group"
            3 -> tab.text = "Settings"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.profileViewPager.adapter = ProfilePagerAdapter(this)
        val tabLayoutMediator = TabLayoutMediator(
            binding.profileTab, binding.profileViewPager, configure
        )
        binding.profileName.text = prefsHelper.getUser()?.name
        binding.profilePic.load(R.drawable.pro_pic_placeholder) {
            transformations(CircleCropTransformation())
        }
        binding.btCreatePost.setOnClickListener(this)
        binding.btAddDonateHistory.setOnClickListener(this)
        binding.fabUpdateImage.setOnClickListener(this)
        tabLayoutMediator.attach()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProfileImage().observe(viewLifecycleOwner, Observer { it1 ->
            when (it1.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it1.data
                    if (response != null) {
                        if (response.status) {
                            if (response.image != null) {
                                binding.profilePic.load(response.image) {
                                    crossfade(true)
                                    crossfade(300)
                                    placeholder(R.drawable.pro_pic_placeholder)
                                    transformations(CircleCropTransformation())
                                }
                                Log.d(TAG, "onViewCreated: image ${response.image}")
                            } else {

                            }
                        } else {
                            val message = response.messages.toString()
                            Log.d(TAG, "onViewCreated: failed $message")
                            ViewUtils.toastFailedWithMessage(
                                requireActivity(),
                                requireContext(),
                                message
                            )
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    progressVisibility(View.GONE)
                    Log.d(TAG, "onActivityCreated: networkError " + it1.isNetworkError)
                    it1.isNetworkError?.let { it ->
                        if (it) {
                            //ViewUtils.toastNoInternet(requireActivity(), requireContext())
                        }
                    }
                }

                Resource.Status.LOADING ->
                    progressVisibility(View.VISIBLE)
            }
        })
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btCreatePost -> {
                val bottomFragment = CreatePostFragment()
                bottomFragment.show(parentFragmentManager, TAG)
            }
            binding.btAddDonateHistory -> {
                val bottomFragment = AddHistoryFragment()
                bottomFragment.show(parentFragmentManager, TAG)
            }
            binding.fabUpdateImage -> {
                ImagePicker.with(this)
                    .cropSquare()
                    .start()
            }
        }
    }

    companion object {
        private const val TAG = "ProfileFragment"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: outside")

        val fileUri = data?.data
        binding.profilePic.load(fileUri) {
            transformations(CircleCropTransformation())
        }
        file = ImagePicker.getFile(data)
        file?.let { showSnackBar() }
        Log.d(TAG, "onActivityResult: inside")

    }

    private fun showSnackBar() {
        val snackbar = Snackbar.make(binding.root, "Profile Image is being updated", Snackbar.LENGTH_LONG)
        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar, event: Int) {
                if (event != DISMISS_EVENT_ACTION) if (event == DISMISS_EVENT_TIMEOUT) {
                    uploadImage()
                }
            }
        })
        snackbar.setAction("UNDO", View.OnClickListener { }).setActionTextColor(Color.GREEN)
        snackbar.show()
    }

    private fun uploadImage() {
        file?.let {
            viewModel.uploadImage(it)?.observe(viewLifecycleOwner, Observer {it1->
                when (it1.status) {
                    Resource.Status.SUCCESS -> {
                        progressVisibility(View.GONE)
                        val response = it1.data
                        if (response != null) {
                            if (response.status) {
                                Toast.makeText(
                                    requireContext(),
                                    response.messages[0],
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val message = response.messages.toString()
                                Log.d(TAG, "onViewCreated: failed $message")
                                ViewUtils.toastFailedWithMessage(requireActivity(), requireContext(), message)
                            }
                        }
                    }
                    Resource.Status.ERROR -> {
                        progressVisibility(View.GONE)
                        Log.d(TAG, "onActivityCreated: networkError " + it1.isNetworkError)
                        it1.isNetworkError?.let { it ->
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
        binding.pbProfile.visibility = visibility
    }

}