package com.decimalab.minutehelp.ui.profile.settings.password

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentPasswordBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.profile.settings.basic.BasicFragment
import com.decimalab.minutehelp.ui.profile.settings.basic.BasicViewModel
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PasswordFragment : DaggerFragment(), View.OnClickListener {

    companion object {
        fun newInstance() = PasswordFragment()
        private const val TAG = "PasswordFragment"
    }

    private lateinit var builder: AlertDialog.Builder
    private lateinit var binding: FragmentPasswordBinding

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: PasswordViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_password, container, false)
        binding.viewModel = viewModel
        binding.btnCancel.setOnClickListener(this)
        builder = AlertDialog.Builder(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.showAlert.observe(viewLifecycleOwner, Observer {
            if (it)
                showAlertDialog()
        })
    }

    private fun showAlertDialog() {

        builder.setMessage("Sure to update?")

        builder.setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    update()
                }
                .setNegativeButton("No") { dialog, id -> //  Action for 'NO' Button
                    dialog.cancel()
                }
        //Creating dialog box
        //Creating dialog box
        val alert = builder.create()

        alert.show()
    }

    private fun update() {
        viewModel.update().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        if (response.code == 200 && response.status) {
                            Toast.makeText(
                                    requireContext(),
                                    response.messages[0],
                                    Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val message = response.messages.toString()
                            Log.d(TAG, "onViewCreated: failed $message")
                            ViewUtils.toastFailedWithMessage(requireActivity(), requireContext(), message)
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    progressVisibility(View.GONE)
                    Log.d(TAG, "onActivityCreated: networkError " + it.isNetworkError)
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
        binding.pbPassword.visibility = visibility
    }

    override fun onClick(v: View?) {
        when(v){
            binding.btnCancel->{
                requireActivity().onBackPressed()
            }
        }
    }


}