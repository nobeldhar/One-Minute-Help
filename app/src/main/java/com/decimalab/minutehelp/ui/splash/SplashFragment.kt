package com.decimalab.minutehelp.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SplashFragment : DaggerFragment() {


    @Inject
    lateinit var prefsHelper: SharedPrefsHelper
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            val navDirections: NavDirections = if (prefsHelper.hasUser())
                SplashFragmentDirections.actionNavSplashToNavHome()
            else
                SplashFragmentDirections.actionNavSplashToNavLogin()
            findNavController().navigate(navDirections)
        }, 2000)

        return root
    }

    override fun onResume() {
        super.onResume()

    }

}