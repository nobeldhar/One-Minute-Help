package com.decimalab.minutehelp.ui.verifycode

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.Receiver.OTPReceiveListener
import com.decimalab.minutehelp.Receiver.SMSReceiver
import com.decimalab.minutehelp.Receiver.SMSReceiver.Companion.SMS_CONSENT_REQUEST
import com.decimalab.minutehelp.databinding.FragmentVerfyCodeBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.ViewUtils
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import dagger.android.support.DaggerFragment
import www.sanju.motiontoast.MotionToast
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject


class VerifyCodeFragment : DaggerFragment(), OTPReceiveListener, View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel by viewModels<VerifyCodeViewModel> { viewModelFactory }
    private lateinit var smsReceiver: SMSReceiver
    private lateinit var binding: FragmentVerfyCodeBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_verfy_code, container, false)
        binding.viewModel = viewModel
        binding.tvResendCode.setOnClickListener(this)
        startSMSListener()
        return binding.root
    }

    private fun startSMSListener() {
        try {
            smsReceiver = SMSReceiver()
            smsReceiver.fragment = this
            smsReceiver.otpListener = this
            val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
            requireActivity().registerReceiver(smsReceiver, intentFilter)
            val task = context?.let { SmsRetriever.getClient(it).startSmsUserConsent(null) }
            task?.addOnSuccessListener(OnSuccessListener {
                Log.d(TAG, "startSMSListener: API success")
            })
            task?.addOnFailureListener(OnFailureListener {
                Log.d(TAG, "startSMSListener: API failure")
            })
        } catch (e: Exception) {
            Log.d(TAG, "startSMSListener: ${e.message}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getVerifyResult.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        if (response.code == 200 && response.status) {
                            val message = response.messages.toString()
                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                            findNavController().navigate(VerifyCodeFragmentDirections.actionNavVerifyCodeToNavHome())
                        } else {
                            val message = response.messages.toString()
                            Log.d(TAG, "onViewCreated: failed $message")
                            ViewUtils.toastFailedWithMessage(requireActivity(), requireContext(), message)
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    progressVisibility(View.GONE)
                    Log.d(TAG, "onActivityCreated: error " + it.isNetworkError)
                    it.isNetworkError?.let { it ->
                        if (it) {
                            ViewUtils.toastNoInternet(requireActivity(), requireContext())
                        }
                    }
                }
                Resource.Status.LOADING ->
                    progressVisibility(View.VISIBLE)
            }
        })

        viewModel.getResendOTPResult.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        if (response.code == 201 && response.status) {
                            val message = response.messages.toString()
                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                            Toast.makeText(requireContext(), "Wait for the code", Toast.LENGTH_SHORT).show()
                        } else {
                            val message = response.messages.toString()
                            Log.d(TAG, "onViewCreated: failed $message")
                            ViewUtils.toastFailedWithMessage(requireActivity(), requireContext(), message)
                        }
                    }

                }
                Resource.Status.ERROR -> {
                    progressVisibility(View.GONE)
                    Log.d(TAG, "onActivityCreated: error " + it.isNetworkError)
                    it.isNetworkError?.let { it ->
                        if (it) {
                            ViewUtils.toastNoInternet(requireActivity(), requireContext())
                        }
                    }
                }
                Resource.Status.LOADING ->
                    progressVisibility(View.VISIBLE)

            }
        })
    }

    private fun progressVisibility(visibility: Int) {
        binding.pbVerify.visibility = visibility
    }

    override fun onOTPReceived(otp: String?) {

    }

    override fun onOTPTimeOut() {

    }

    override fun onOTPReceivedError(error: String?) {
        Log.d(TAG, "onOTPReceivedError: $error")
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: outside result")
        when (requestCode) {
            // ...
            SMS_CONSENT_REQUEST -> {
                // Obtain the phone number from the result
                if (resultCode == Activity.RESULT_OK && data != null) {
                    // Get SMS message content
                    val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                    // Extract one-time code from the message and complete verification
                    // `message` contains the entire text of the SMS message, so you will need
                    // to parse the string.
                    Log.d(TAG, "onActivityResult: $message")
                    message?.let { parseOneTimeCode(it) } // define this function
                    // send one time code to the server
                } else {
                    Log.d(TAG, "onActivityResult: User can type OTC manually")
                    // Consent denied. User can type OTC manually.
                }
                Log.d(TAG, "onActivityResult: inside result")
            }
        }
    }

    private fun parseOneTimeCode(message: String) {
        val pattern: Pattern = Pattern.compile("(|^)\\d{4}")
        val matcher: Matcher = pattern.matcher(message)
        if (matcher.find()) {
            binding.etPinEntry.text = Editable.Factory.getInstance().newEditable(matcher.group(0))
            Log.d(TAG, "parseOneTimeCode: ${matcher.group(0)}")
            viewModel.onVerifyClicked(matcher.group(0))
        }
    }

    private fun onRendCodeClicked(){
        startSMSListener()
        viewModel.onResendOTPClicked()
    }

    companion object {
        private const val TAG = "VerifyCodeFragment"
    }

    override fun onDestroy() {
        super.onDestroy()
        smsReceiver.let {
            requireActivity().unregisterReceiver(smsReceiver)
        }
    }

    override fun onClick(v: View?) {
        when(v){
            binding.tvResendCode->onRendCodeClicked()
        }
    }

}