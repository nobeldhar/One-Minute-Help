package com.decimalab.minutehelp.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentLoginBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerFragment
import www.sanju.motiontoast.MotionToast
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.fragment = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLoginResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        if (response.code == 200 && response.status) {
                            if(response.data.isVerified == 0){
                                val action = LoginFragmentDirections.actionNavLoginToNavVerifyCode()
                                findNavController().navigate(action)
                            }else{
                                val action = LoginFragmentDirections.actionNavLoginToNavHome()
                                findNavController().navigate(action)
                            }
                        } else {
                            val message = response.messages.toString()
                            Log.d(TAG, "onViewCreated: failed $message")

                            MotionToast.darkToast(
                                requireActivity(),
                                "Failed ☹️",
                                message,
                                MotionToast.TOAST_ERROR,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular)
                            )
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    progressVisibility(View.GONE)
                    Log.d(Companion.TAG, "onActivityCreated: error " + it.isNetworkError)

                    if (it.isNetworkError!!)
                    {
                        MotionToast.darkToast(
                            requireActivity(),
                            "Failed ☹️",
                            "No Internet!",
                            MotionToast.TOAST_ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular)
                        )
                    }


                }

                Resource.Status.LOADING ->
                    progressVisibility(View.VISIBLE)

            }
        })

        viewModel._errorUiLogin.observe(viewLifecycleOwner, {

            MotionToast.darkToast(
                requireActivity(),
                "Failed ☹️",
                it,
                MotionToast.TOAST_WARNING,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular)
            )
        })
    }

    private fun progressVisibility(visible: Int) {
        binding.pbLogin.visibility = visible
    }

    fun onRegisterClicked() {
        val action = LoginFragmentDirections.actionNavLoginToNavRegister()
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        ViewUtils.hideToolbar(requireActivity())
        ViewUtils.hideStatusBar(requireActivity())
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}