package com.decimalab.minutehelp.ui.groupdetails.groupcreatepost

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.databinding.FragmentCreatePostBinding
import com.decimalab.minutehelp.databinding.FragmentGroupCreatePostBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.ui.profile.settings.information.InformationFragment
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.ViewUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class GroupCreatePostFragment(val groupId: Int) : BottomSheetDialogFragment(), View.OnClickListener, View.OnTouchListener {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @Inject
    lateinit var prefsHelper: SharedPrefsHelper
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: GroupCreatePostViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentGroupCreatePostBinding
    private lateinit var builder: AlertDialog.Builder
    private lateinit var picker: DatePickerDialog
    private lateinit var timePicker: TimePickerDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group_create_post, container, false)
        binding.viewModel = viewModel
        binding.btPublish.setOnClickListener(this)
        builder = AlertDialog.Builder(requireActivity())
        binding.etDatePicker.setOnTouchListener(this)
        binding.etTimePicker.setOnTouchListener(this)
        initializeSpinners(bloodVolumes, binding.spBloodVolumeCp)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDistricts().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        val list = response.data
                        districtsMap?.clear()
                        districtsMap = HashMap()
                        for (item in list)
                            districtsMap!![item.name] = item.id
                        districts = ArrayList()
                        districts = districtsMap!!.keys.toList()
                        initializeSpinners(districts as ArrayList<String>, binding.spDistrictCp)
                    }
                }
                Resource.Status.ERROR -> {
                    progressVisibility(View.GONE)
                    Log.d(TAG, "onViewCreated: networkError " + it.isNetworkError)
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

        viewModel.getBloodList().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        val list = response.data
                        bloodsMap?.clear()
                        bloodsMap = HashMap()
                        for (item in list)
                            bloodsMap!![item.blood] = item.id
                        bloods = ArrayList()
                        bloods = bloodsMap!!.keys.toList()
                        initializeSpinners(bloods as ArrayList<String>, binding.spBloodGroupCp)
                    }
                }
                Resource.Status.ERROR -> {
                    progressVisibility(View.GONE)
                    Log.d(TAG, "onViewCreated: networkError " + it.isNetworkError)
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


        viewModel.getThanaResult.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        val list = response.data
                        thanasMap?.clear()
                        thanasMap = HashMap()
                        for (item in list)
                            thanasMap!![item.name] = item.id
                        thanas = ArrayList()
                        thanas = thanasMap!!.keys.toList()
                        initializeSpinners(thanas as ArrayList<String>, binding.spUpzillaThanaCp)

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

        viewModel.getCityResult.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        val list = response.data
                        citiesMap?.clear()
                        citiesMap = HashMap()
                        for (item in list)
                            citiesMap!![item.name] = item.id
                        cities = ArrayList()
                        cities = citiesMap!!.keys.toList()
                        initializeSpinners(cities as ArrayList<String>, binding.spVillageCityCp)

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

    private fun initializeSpinners(bGroups: List<String>, sp: AppCompatSpinner) {
        val aa = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, bGroups)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        with(sp) {
            adapter = aa
            onItemSelectedListener = viewModel
            gravity = android.view.Gravity.CENTER
        }


    }


    companion object {
        var districts: List<String>? = null
        var thanas: List<String>? = null
        var cities: List<String>? = null
        var bloods: List<String>? = null
        var districtsMap: HashMap<String, Int>? = null
        var thanasMap: HashMap<String, Int>? = null
        var citiesMap: HashMap<String, Int>? = null
        var bloodsMap: HashMap<String, Int>? = null
        val bloodVolumes = listOf<String>("1 bag", "2 bags", "3 bags", "4 bags", "5 bags", "6 bags", "7 bags", "8 bags", "9 bags", "10 bags")
        private const val TAG = "CreatePostFragment"
    }

    private fun progressVisibility(visibility: Int) {
        binding.pbCp.visibility = visibility
    }

    override fun onClick(v: View?) {
        when(v){
            binding.btPublish -> {
                Log.d(TAG, "onClick: ")
                showSnackBar()
            }
        }
    }

    private fun showSnackBar() {
        val snackbar = Snackbar.make(binding.root, "Post is being published", Snackbar.LENGTH_LONG)
        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar, event: Int) {
                if (event != DISMISS_EVENT_ACTION) if (event == DISMISS_EVENT_TIMEOUT) {
                    publishPost()
                }
            }
        })
        snackbar.setAction("UNDO", View.OnClickListener { }).setActionTextColor(Color.GREEN)
        snackbar.show()
    }

    private fun publishPost() {
        viewModel.onPublishClicked(groupId).observe(viewLifecycleOwner, Observer {
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
                            this.dismiss()
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

    private fun showDatePicker() {
        Log.d(TAG, "showDatePicker: inside")
        binding.etDatePicker.isCursorVisible = true

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            binding.etDatePicker.setText("$year-${month + 1}-$dayOfMonth")
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            picker = DatePickerDialog(requireContext(), dateSetListener, viewModel.year, viewModel.month, viewModel.day)
            picker.show()
        }
    }

    private fun showTimePicker(){
        timePicker = TimePickerDialog(requireActivity(),
                OnTimeSetListener { view, hourOfDay, minute ->
                    binding.etTimePicker.setText("$hourOfDay:$minute") }, viewModel.hour, viewModel.minute, false)
        timePicker.show()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_UP){
            when(v){
                binding.etDatePicker -> {
                    showDatePicker()
                    return true
                }
                binding.etTimePicker->{
                    showTimePicker()
                    return true
                }
            }
        }
        return false
    }




}