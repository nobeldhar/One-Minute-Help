package com.decimalab.minutehelp.ui.profile.settings.address

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.remote.responses.DistrictResponse
import com.decimalab.minutehelp.data.repository.SettingsRepository
import com.decimalab.minutehelp.utils.Resource
import javax.inject.Inject

class
AddressViewModel
@Inject constructor(
        val settingsRepository: SettingsRepository
) : ViewModel(), AdapterView.OnItemSelectedListener {

    var district = MutableLiveData<Int>()
    var thana = MutableLiveData<Int>()
    var city = 1
    var postcode = ""

    var showAlert = MutableLiveData<Boolean>()

    val getThanaResult = Transformations.switchMap(district) {
        settingsRepository.getThanas(it)
    }

    val getCityResult = Transformations.switchMap(thana) {
        settingsRepository.getCity(it)
    }
    fun getDistricts(): LiveData<Resource<DistrictResponse>> {
        return settingsRepository.getDistricts()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            R.id.sp_district->{
                district.value = position + 1

            }
            R.id.sp_upzilla_thana->{
                thana.value = position + 1
            }
            R.id.sp_village_city->
                city = position + 1
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