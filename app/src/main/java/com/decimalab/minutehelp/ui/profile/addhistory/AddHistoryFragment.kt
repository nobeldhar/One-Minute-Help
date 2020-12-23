package com.decimalab.minutehelp.ui.profile.addhistory

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentAddHistoryBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.profile.createpost.CreatePostViewModel
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AddHistoryFragment : BottomSheetDialogFragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @Inject
    lateinit var prefsHelper: SharedPrefsHelper
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: AddHistoryViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentAddHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_history, container, false)
        return binding.root
    }



}