package com.decimalab.minutehelp.ui.profile.settings.basic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
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
import com.decimalab.minutehelp.databinding.FragmentBasicBinding
import com.decimalab.minutehelp.databinding.FragmentInformationBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.profile.settings.information.InformationFragment
import com.decimalab.minutehelp.ui.profile.settings.information.InformationViewModel
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class BasicFragment : DaggerFragment(), View.OnClickListener {

    companion object {
        fun newInstance() = BasicFragment()
        private const val TAG = "BasicFragment"
    }

    private lateinit var builder: AlertDialog.Builder

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: BasicViewModel by viewModels { viewModelFactory }
    @Inject
    lateinit var prefsHelper: SharedPrefsHelper
    private lateinit var binding: FragmentBasicBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basic, container, false)
        binding.viewModel = viewModel
        binding.btnCancel.setOnClickListener(this)
        builder = AlertDialog.Builder(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.ui.observe(viewLifecycleOwner, Observer {
            if (it.equals("Clicked")){
                showAlertDialog()
            }
        })
    }

    private fun showAlertDialog() {

        builder.setTitle("Sure to update?")

        val message = "Name : ${viewModel.name}\n" +
                "Email : ${viewModel.email}"

        builder.setMessage(message)
                .setCancelable(false)
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
        binding.pbBasic.visibility = visibility
    }

    override fun onClick(v: View?) {
        when(v){
            binding.btnCancel->{
                requireActivity().onBackPressed()
            }
        }
    }


}