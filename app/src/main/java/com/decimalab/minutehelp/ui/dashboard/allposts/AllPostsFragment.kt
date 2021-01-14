package com.decimalab.minutehelp.ui.dashboard.allposts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.decimalab.minutehelp.R

class AllPostsFragment : Fragment() {

    companion object {
        fun newInstance() = AllPostsFragment()
    }

    private lateinit var viewModel: AllPostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_posts, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllPostsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}