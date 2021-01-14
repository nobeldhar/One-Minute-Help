package com.decimalab.minutehelp.ui.dashboard.topdoner

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.decimalab.minutehelp.R

class TopDonorsFragment : Fragment() {

    companion object {
        fun newInstance() = TopDonorsFragment()
    }

    private lateinit var viewModel: TopDonorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_donors, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopDonorsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}