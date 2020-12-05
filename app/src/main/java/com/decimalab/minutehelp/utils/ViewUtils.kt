package com.decimalab.minutehelp.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import com.decimalab.minutehelp.R
import com.google.android.material.snackbar.Snackbar
import www.sanju.motiontoast.MotionToast

object ViewUtils {

    fun hideStatusBar(activity: Activity) {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
    fun hideToolbar(activity: Activity) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    fun View.visible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun View.enable(enabled: Boolean) {
        isEnabled = enabled
        alpha = if (enabled) 1f else 0.5f
    }

    fun toastNoInternet(requireActivity: FragmentActivity, requireContext: Context) {
        MotionToast.darkToast(
                requireActivity,
                "Failed ☹️",
                "No Internet!",
                MotionToast.TOAST_ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(requireContext, R.font.helvetica_regular)
        )
    }

    fun toastFailedWithMessage(requireActivity: FragmentActivity, requireContext: Context, message: String) {
        MotionToast.darkToast(
                requireActivity,
                "Failed ☹️",
                message,
                MotionToast.TOAST_ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(requireContext, R.font.helvetica_regular)
        )
    }


    fun View.warningSnackbar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
            snackbar.setAction("Ok") {
                snackbar.dismiss()
            }
        }.show()
    }

    fun View.snackbar(message: String, action: (() -> Unit)? = null) {
        val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        action?.let {
            snackbar.setAction("Retry") {
                it()
            }
        }
        snackbar.show()
    }


}