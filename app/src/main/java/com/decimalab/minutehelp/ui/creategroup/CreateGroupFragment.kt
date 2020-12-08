package com.decimalab.minutehelp.ui.creategroup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.decimalab.minutehelp.R

class CreateGroupFragment : Fragment() {

    companion object {
        fun newInstance() = CreateGroupFragment()
    }

    private lateinit var viewModel: CreateGroupViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_group, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateGroupViewModel::class.java)
        // TODO: Use the ViewModel
    }

}