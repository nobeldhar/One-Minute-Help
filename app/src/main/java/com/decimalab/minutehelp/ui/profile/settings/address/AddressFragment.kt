package com.decimalab.minutehelp.ui.profile.settings.address

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
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
import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.databinding.FragmentAddressBinding
import com.decimalab.minutehelp.factory.AppViewModelFactory
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import com.decimalab.minutehelp.utils.ViewUtils
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AddressFragment : DaggerFragment(), View.OnClickListener {


    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private val viewModel: AddressViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var prefsHelper: SharedPrefsHelper
    private lateinit var binding: FragmentAddressBinding
    private lateinit var builder: AlertDialog.Builder


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_address, container, false)
        initializeVM()
        binding.viewModel = viewModel
        binding.btnCancel.setOnClickListener(this)
        builder = AlertDialog.Builder(requireActivity())
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
                        districtsMap?.clear()
                        districtsMap = HashMap()
                        for (item in response)
                            districtsMap!![item.name] = item.id
                        districts = ArrayList()
                        districts = districtsMap?.keys?.toList()
                        Companion.districts?.let { it1 -> initializeSpinners(it1, binding.spDistrict) }
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


        viewModel.getThanaResult.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressVisibility(View.GONE)
                    val response = it.data
                    if (response != null) {
                        thanasMap?.clear()
                        thanasMap = HashMap()
                        for (item in response)
                            thanasMap!![item.name] = item.id
                        thanas = ArrayList()
                        thanas = thanasMap?.keys?.toList()

                        Companion.thanas?.let { it1 -> initializeSpinners(it1, binding.spUpzillaThana) }

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

                        citiesMap?.clear()
                        citiesMap = HashMap()
                        for (item in response)
                            citiesMap!![item.name] = item.id
                        cities = ArrayList()
                        cities = citiesMap?.keys?.toList()
                        Companion.cities?.let { it1 -> initializeSpinners(it1, binding.spVillageCity) }
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

        viewModel.showAlert.observe(viewLifecycleOwner, Observer {
            if (it)
                showSnackBar()
        })
    }

    fun initializeVM(){
        prefsHelper.getAddress()?.let {
            it.district?.let {it1->
                viewModel.district = it1
                viewModel.thana= it.thana.toString()
                viewModel.city = it.city.toString()
                viewModel.postcode = it.postcode.toString()
                Log.d(TAG, "onViewCreated: address not null")
            }
        }
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
        var districtsMap: HashMap<String, Int>? = null
        var thanasMap: HashMap<String, Int>? = null
        var citiesMap: HashMap<String, Int>? = null
        private const val TAG = "AddressFragment"
    }

    private fun progressVisibility(visibility: Int) {
        binding.pbAddress.visibility = visibility
    }


    private fun showSnackBar() {
        val snackbar = Snackbar.make(binding.root, "Address is being updated", Snackbar.LENGTH_LONG)
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

    private fun showAlertDialog() {

        builder.setTitle("Sure to update?")

        var message = ""
        if (districts?.size != 0 && thanas?.size != 0 && cities?.size != 0) {
            message = "District : ${viewModel.district_id.value?.minus(1)?.let { districts?.get(it) }}\n" +
                    "Upzilla/Thana : ${viewModel.thana_id.value?.minus(1)?.let { thanas?.get(it) }}\n" +
                    "Village/City : ${viewModel.city_id?.minus(1)?.let { cities?.get(it) }}\n" +
                    "Postcode : ${viewModel.postcode}"
        }


        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->

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
                        if (response.status) {
                            Toast.makeText(
                                    requireContext(),
                                    response.messages[0],
                                    Toast.LENGTH_SHORT
                            ).show()

                            prefsHelper.updateAddress(
                                    SettingsRequest(
                                            district = viewModel.district_id.value?.let { it1 ->
                                                getKey(
                                                    districtsMap, it1
                                                )
                                            },
                                            thana = viewModel.thana_id.value?.let { it1 ->
                                                getKey(
                                                    thanasMap, it1
                                                )
                                            },
                                            city = viewModel.city_id?.let { it1 ->
                                                getKey(
                                                    citiesMap, it1
                                                )
                                            },
                                            postcode = viewModel.postcode))
                            initializeVM()
                            updateUI()

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

    private fun updateUI() {
        binding.tbDistrict.text = viewModel.district
        binding.tbThana.text = viewModel.thana
        binding.tbCity.text = viewModel.city
        binding.tbPostcode.text = viewModel.postcode
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