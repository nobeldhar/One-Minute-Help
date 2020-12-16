package com.decimalab.minutehelp.ui.profile.settings.address

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
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AddressFragment : DaggerFragment() {


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
        binding.viewModel = viewModel
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
                        val districts = ArrayList<String>()
                        for (item in response)
                            districts.add(item.name)
                        Companion.districts = districts
                        initializeSpinners(Companion.districts as ArrayList<String>, binding.spDistrict, prefsHelper.getAddress()?.district_id?.minus(1))
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
                        val thanas = ArrayList<String>()
                        for (item in response)
                            thanas.add(item.name)
                        Companion.thanas = thanas
                        initializeSpinners(Companion.thanas as ArrayList<String>, binding.spUpzillaThana, prefsHelper.getAddress()?.thana_id?.minus(1))

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

                        val cities = ArrayList<String>()
                        for (item in response)
                            cities.add(item.name)
                        Companion.cities = cities
                        initializeSpinners(Companion.cities as ArrayList<String>, binding.spVillageCity, prefsHelper.getAddress()?.city_id?.minus(1))

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

        prefsHelper.getAddress()?.let {
            viewModel.district.value = it.district_id
            viewModel.thana.value = it.thana_id
            viewModel.postcode = it.postcode.toString()
        }

        viewModel.showAlert.observe(viewLifecycleOwner, Observer {
            if (it)
                showAlertDialog()
        })
    }

    private fun initializeSpinners(bGroups: List<String>, sp: AppCompatSpinner, position: Int?) {
        val aa = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, bGroups)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        with(sp) {
            adapter = aa
            setSelection(position ?: 0, false)
            onItemSelectedListener = viewModel
            gravity = android.view.Gravity.CENTER
        }


    }

    companion object {
        var districts: List<String>? = null
        var thanas: List<String>? = null
        var cities: List<String>? = null
        private const val TAG = "AddressFragment"
    }

    private fun progressVisibility(visibility: Int) {
        binding.pbAddress.visibility = visibility
    }

    private fun showAlertDialog() {

        builder.setTitle("Sure to update?")

        var message = ""
        if (districts?.size != 0 && thanas?.size != 0 && cities?.size != 0){
            message = "District : ${viewModel.district.value?.minus(1)?.let { districts?.get(it) }}\n" +
                    "Upzilla/Thana : ${viewModel.thana.value?.minus(1)?.let { thanas?.get(it) }}\n" +
                    "Village/City : ${viewModel.city.minus(1)?.let { cities?.get(it) }}\n" +
                    "Postcode : ${viewModel.postcode}"
        }


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

                            prefsHelper.updateAddress(
                                    SettingsRequest(
                                            district_id = viewModel.district.value,
                                            thana_id = viewModel.thana.value,
                                            city_id = viewModel.city,
                                            postcode = viewModel.postcode))

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

}