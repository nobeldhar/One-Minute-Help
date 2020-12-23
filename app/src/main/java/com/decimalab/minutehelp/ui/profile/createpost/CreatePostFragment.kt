package com.decimalab.minutehelp.ui.profile.createpost

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentCreatePostBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.profile.ProfileViewModel
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CreatePostFragment : BottomSheetDialogFragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @Inject
    lateinit var prefsHelper: SharedPrefsHelper
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: CreatePostViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentCreatePostBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_post, container, false)
        binding.createName.text = prefsHelper.getUser()?.name
        return binding.root
    }



}