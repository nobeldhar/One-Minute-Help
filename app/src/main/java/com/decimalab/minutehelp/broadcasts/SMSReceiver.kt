package com.decimalab.minutehelp.broadcasts

import android.content.ActivityNotFoundException
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import dagger.android.support.DaggerFragment


class SMSReceiver() : BroadcastReceiver() {

    var otpListener: OTPReceiveListener? = null
    var fragment : DaggerFragment? = null

    companion object {
        val SMS_CONSENT_REQUEST = 2323
        private const val TAG = "SMSReceiver"
    }


    override fun onReceive(context: Context?, intent: Intent?) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent!!.action) {
            val extras = intent.extras
            val status: Status? = extras!![SmsRetriever.EXTRA_STATUS] as Status?
            when (status?.statusCode) {
                CommonStatusCodes.SUCCESS -> {


                    val consentIntent = extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                    try {
                        // Start activity to show consent dialog to user, activity must be started in
                        // 5 minutes, otherwise you'll receive another TIMEOUT intent
                        fragment?.startActivityForResult(consentIntent, SMS_CONSENT_REQUEST)
                    } catch (e: ActivityNotFoundException) {
                        Log.d(Companion.TAG, "onReceive: ${e.message}")
                    }
                    //This is the full message
                    val message = extras[SmsRetriever.EXTRA_SMS_MESSAGE] as String?

                    /*<#> Your ExampleApp code is: 123ABC78
                    FA+9qCX9VSu*/

                    //Extract the OTP code and send to the listener
                    //otpListener?.onOTPReceived(message)
                }
                CommonStatusCodes.TIMEOUT ->                     // Waiting for SMS timed out (5 minutes)
                    otpListener?.onOTPTimeOut()
                CommonStatusCodes.API_NOT_CONNECTED -> otpListener?.onOTPReceivedError("API NOT CONNECTED")
                CommonStatusCodes.NETWORK_ERROR -> otpListener?.onOTPReceivedError("NETWORK ERROR")
                CommonStatusCodes.ERROR -> otpListener?.onOTPReceivedError("SOME THING WENT WRONG")
            }
        }
    }
}