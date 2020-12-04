package com.decimalab.minutehelp.ui.profile.donatehistory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.decimalab.minutehelp.R

class DonateHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = DonateHistoryFragment()
    }

    private lateinit var viewModel: DonateHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_donate_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DonateHistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}