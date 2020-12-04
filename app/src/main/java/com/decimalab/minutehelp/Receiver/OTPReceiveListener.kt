package com.decimalab.minutehelp.Receiver

interface OTPReceiveListener {

    fun onOTPReceived(otp: String?)

    fun onOTPTimeOut()

    fun onOTPReceivedError(error: String?)
}