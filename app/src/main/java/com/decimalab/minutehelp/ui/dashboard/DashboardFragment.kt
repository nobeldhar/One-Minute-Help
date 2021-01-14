package com.decimalab.minutehelp.ui.dashboard

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentDashbaordBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class DashboardFragment : DaggerFragment(){

    companion object {
        fun newInstance() = DashboardFragment()
        private const val TAG = "HomeFragment"
    }

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel by viewModels<DashboardViewModel> { viewModelFactory }

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper
    private lateinit var binding: FragmentDashbaordBinding
    private lateinit var connectivityManager: ConnectivityManager
    private val networkCallback = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d(TAG, "onAvailable: ${network.toString()}")
            viewModel._verifyAuthToken.postValue(true)
        }
    }
    private val configure = TabLayoutMediator.TabConfigurationStrategy { tab, position ->
        when (position) {
            0 -> tab.text = "All Posts"
            1 -> tab.text = "Members"
            2 -> tab.text = "Group"
            3 -> tab.text = "Top Donors"
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashbaord, container, false)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            initConnectivity()
        }
        binding.dashboardViewPager.adapter = DashboardPagerAdapter(this)
        val tabLayoutMediator = TabLayoutMediator(
            binding.dashboardTab, binding.dashboardViewPager, configure
        )

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initConnectivity() {
        connectivityManager = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.nav_home
                )
        )
        binding.homeToolbar.setupWithNavController(findNavController(), appBarConfiguration)*/

        viewModel.getVerifyTokenResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    val response = it.data
                    if (response != null) {
                        if (response.code == 201 && !response.status) {
                            Log.d(TAG, "onNetworkActive: Token Not Active, Response code: ${response.code}")
                            findNavController().navigate(DashboardFragmentDirections.actionNavHomeToNavLogin())
                        } else {
                            Log.d(TAG, "onNetworkActive: Token Active, Response code: ${response.code}")
                        }
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }


}