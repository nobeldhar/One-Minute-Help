package com.decimalab.minutehelp.ui.profile.settings.address

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.local.entities.District
import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.remote.responses.DistrictResponse
import com.decimalab.minutehelp.data.repository.SettingsRepository
import com.decimalab.minutehelp.ui.profile.createpost.CreatePostFragment
import com.decimalab.minutehelp.utils.Resource
import com.decimalab.minutehelp.utils.SharedPrefsHelper
import javax.inject.Inject

class
AddressViewModel
@Inject constructor(
        val settingsRepository: SettingsRepository,
        val prefsHelper: SharedPrefsHelper
) : ViewModel(), AdapterView.OnItemSelectedListener {

    var district = MutableLiveData<Int>()
    var thana = MutableLiveData<Int>()
    var city: Int? = null
    var postcode = ""

    var showAlert = MutableLiveData<Boolean>()

    val getThanaResult = Transformations.switchMap(district) {
        settingsRepository.getThanas(it)
    }

    val getCityResult = Transformations.switchMap(thana) {
        settingsRepository.getCity(it)
    }
    fun getDistricts(): LiveData<Resource<List<District>>> {
        return settingsRepository.getDistricts()
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            R.id.sp_district->{
                district.value = AddressFragment.districts?.let {
                    AddressFragment.districtsMap?.get(it.get(position))
                }!!

            }
            R.id.sp_upzilla_thana->{
                thana.value =  AddressFragment.thanas?.let {
                    AddressFragment.thanasMap?.get(it.get(position))
                }!!
            }
            R.id.sp_village_city->
                city =  AddressFragment.cities?.let {
                    AddressFragment.citiesMap?.get(it.get(position))
                }!!
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun onUpdateClicked(){
        showAlert.value = true
    }

    fun update(): LiveData<Resource<AuthResponse>> {
        val settingsRequest = SettingsRequest(district_id = district.value, thana_id = thana.value, city_id = city, postcode = postcode)
        return settingsRepository.updateAddress(settingsRequest = settingsRequest)
    }
}