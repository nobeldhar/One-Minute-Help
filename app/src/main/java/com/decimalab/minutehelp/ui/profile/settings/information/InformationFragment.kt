package com.decimalab.minutehelp.ui.profile.settings.information

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.databinding.FragmentInformationBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.profile.settings.address.AddressFragment
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.ViewUtils
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class InformationFragment : DaggerFragment(),  View.OnTouchListener, View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: InformationViewModel by viewModels { viewModelFactory }
    @Inject
    lateinit var prefsHelper: SharedPrefsHelper
    private lateinit var binding: FragmentInformationBinding
    private lateinit var picker: DatePickerDialog
    private lateinit var builder: AlertDialog.Builder

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false)
        initialiseVM()
        binding.viewModel = viewModel
        binding.btnCancel.setOnClickListener(this)
        binding.etDateOfBirth.setOnTouchListener(this)
        builder = AlertDialog.Builder(requireContext())
        return binding.root
    }

    private fun initialiseVM() {
        prefsHelper.getInfo()?.let {
            it.blood?.let {it1->
                viewModel.bloodGroup = it1
                viewModel.gender= it.gender.toString()
                viewModel.pDoB = it.date_of_birth.toString()
                Log.d(TAG, "onViewCreated: address not null")
            }
        }
    }

    private fun initializeSpinners(bGroups: List<String>) {
        val aa = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, bGroups)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        with(binding.spBloodGroup){
            adapter = aa
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
                showSnackBar()
            }
        })
        viewModel.getBloodList().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        if (response.code == 200 && response.status) {
                            bGHashMap?.clear()
                            bGHashMap = HashMap()
                            for (item in response.data)
                                bGHashMap!![item.blood] = item.id
                            bloodGroups = ArrayList()
                            bloodGroups = bGHashMap?.keys?.toList()
                            Log.d(TAG, "onViewCreated: ${bGHashMap.toString()}")
                            initializeSpinners(bloodGroups as ArrayList<String>)
                        } else {
                            val message = response.messages.toString()
                            Log.d(TAG, "onViewCreated: failed $message")
                            ViewUtils.toastFailedWithMessage(
                                requireActivity(),
                                requireContext(),
                                message
                            )
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

    private fun showSnackBar() {
        val snackbar = Snackbar.make(binding.root, "Other Information is being updated", Snackbar.LENGTH_LONG)
        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar, event: Int) {
                if (event != DISMISS_EVENT_ACTION) if (event == DISMISS_EVENT_TIMEOUT) {
                    update()
                }
            }
        })
        snackbar.setAction("UNDO", View.OnClickListener { }).setActionTextColor(Color.GREEN)
        snackbar.show()
    }

    private fun update() {
        viewModel.update().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        if (response.code == 201 && response.status) {
                            Toast.makeText(
                                    requireContext(),
                                    response.messages[0],
                                    Toast.LENGTH_SHORT
                            ).show()
                            prefsHelper.updateInfo(
                                SettingsRequest(
                                    blood = viewModel.bGroup_id?.let { it1 ->
                                        getKey(
                                            bGHashMap, it1
                                        )
                                    },
                                    gender = genders[viewModel.gender_id-1],
                                    date_of_birth = viewModel.dateOfBirth)
                            )
                            initialiseVM()
                            updateUi()

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

    private fun updateUi() {
        binding.tbBlood.text = viewModel.bloodGroup
        binding.tbDoF.text = viewModel.pDoB
        binding.tbGender.text = viewModel.gender
    }

    private fun progressVisibility(visibility: Int) {
        binding.pbInfo.visibility = visibility
    }

    companion object {

        var bGHashMap: HashMap<String, Int>? = null
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

    override fun onClick(v: View?) {
        when(v){
            binding.btnCancel->{
                requireActivity().onBackPressed()
            }
        }
    }

    fun getKey(hashMap: HashMap<String, Int>?, target: Int): String? {
        val rValue = hashMap?.filter { target == it.value }?.keys?.first()
        Log.d(TAG, "getKey: $rValue")
        return rValue
    }


}