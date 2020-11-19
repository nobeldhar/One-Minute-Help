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
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.utils.ViewUtils

class SplashFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler(Looper.myLooper()!!).postDelayed({
            val navDirections: NavDirections =
                    SplashFragmentDirections.actionNavSplashToNavLogin()
            Navigation.findNavController(root).navigate(navDirections)
        }, 2000)

        return root
    }

    override fun onResume() {
        super.onResume()

        ViewUtils.hideToolbar(requireActivity())
        ViewUtils.hideStatusBar(requireActivity())
    }

}