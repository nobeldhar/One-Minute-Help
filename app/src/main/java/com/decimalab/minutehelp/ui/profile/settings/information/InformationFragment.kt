package com.decimalab.minutehelp.ui.profile.settings.information

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentInformationBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.register.RegisterFragment
import com.decimalab.minutehelp.ui.register.RegisterFragmentDirections
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.ViewUtils
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class InformationFragment : DaggerFragment(),  View.OnTouchListener {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: InformationViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentInformationBinding
    private lateinit var picker: DatePickerDialog
    private lateinit var builder: AlertDialog.Builder

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false)
        binding.viewModel = viewModel
        binding.etDateOfBirth.setOnTouchListener(this)
        builder = AlertDialog.Builder(requireContext())
        return binding.root
    }

    private fun initializeSpinners(bGroups: List<String>) {
        val aa = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, bGroups)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        with(binding.spBloodGroup){
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = viewModel
            prompt = "Select your Blood Group"
            gravity = Gravity.CENTER
        }

        val aa1 = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, Companion.genders)
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        with(binding.spGender){
            adapter = aa1
            setSelection(0, false)
            onItemSelectedListener = viewModel
            prompt = "Select your Blood Group"
            gravity = Gravity.CENTER
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //NavigationUI.setupWithNavController(binding.infoToolbar, findNavController())
        viewModel.uiError.observe(viewLifecycleOwner, Observer {
            if (it.equals("OK")) {
                showAlertDialog()
            }
        })
        viewModel.getBloodList().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        if (response.code == 200 && response.status) {
                            val bG = ArrayList<String>()
                            for (item in response.data)
                                bG.add(item.blood)
                            bloodGroups = bG
                            initializeSpinners(bloodGroups as ArrayList<String>)
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

    private fun showAlertDialog() {

        builder.setTitle("Sure to update?")

        val message = "Blood Group : ${bloodGroups?.get(viewModel.bloodGroup-1)}\n" +
                "Date Of Birth : ${viewModel.dateOfBirth}\n" +
                "Gender : ${genders[viewModel.gender-1]}"


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
        binding.pbInfo.visibility = visibility
    }

    companion object {
        var bloodGroups : List<String>? = null
        val genders = arrayOf("Male", "Female")
        private const val TAG = "InformationFragment"
    }

    private fun showDatePicker() {
        Log.d(Companion.TAG, "showDatePicker: inside")
        binding.etDateOfBirth.isCursorVisible = true

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            binding.etDateOfBirth.setText("$year-${month + 1}-$dayOfMonth")
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            picker = DatePickerDialog(requireContext(), dateSetListener, viewModel.year, viewModel.month, viewModel.day)
            picker.show()
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_UP){
            when(v){
                binding.etDateOfBirth -> {
                    showDatePicker()
                    return true
                }
            }
        }
        return false
    }


}