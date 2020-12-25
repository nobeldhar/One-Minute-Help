package com.decimalab.minutehelp.ui.profile.createpost

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.remote.requests.CreatePostRequest
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.remote.responses.BloodResponse
import com.decimalab.minutehelp.data.remote.responses.DistrictResponse
import com.decimalab.minutehelp.data.repository.ProfileRepository
import com.decimalab.minutehelp.utils.Resource
import java.util.*
import javax.inject.Inject

class
CreatePostViewModel
@Inject constructor(
        val profileRepository: ProfileRepository
) : ViewModel(), AdapterView.OnItemSelectedListener {

    var district = MutableLiveData<Int>()
    var thana = MutableLiveData<Int>()
    var city = 1
    var bloodVol = 1
    var bloodId = 1
    var problem: String? = null
    var hospital: String? = null
    var phone: String? = null

    val cldr: Calendar = Calendar.getInstance();
    val day = cldr.get(Calendar.DAY_OF_MONTH);
    val month = cldr.get(Calendar.MONTH);
    val year = cldr.get(Calendar.YEAR);
    val hour = cldr.get(Calendar.HOUR)
    val minute = cldr.get(Calendar.MINUTE)
    var date = "$year/${month + 1}/$day"
    var time = "$hour:$minute"

    val getThanaResult = Transformations.switchMap(district) {
        profileRepository.getThanas(it)
    }

    val getCityResult = Transformations.switchMap(thana) {
        profileRepository.getCities(it)
    }

    fun getDistricts(): LiveData<Resource<DistrictResponse>> {
        return profileRepository.getDistricts()
    }

    fun getBloodList(): LiveData<Resource<BloodResponse>> {
        return profileRepository.getBloodList()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.sp_district_cp -> {
                district.value = CreatePostFragment.districts?.let {
                    CreatePostFragment.districtsMap?.get(it.get(position))
                }!!

            }
            R.id.sp_upzilla_thana_cp -> {
                thana.value = CreatePostFragment.thanas?.let {
                    CreatePostFragment.thanasMap?.get(it.get(position))
                }!!
            }
            R.id.sp_village_city_cp -> {
                city = CreatePostFragment.cities?.let {
                    CreatePostFragment.citiesMap?.get(it.get(position))
                }!!
            }
            R.id.sp_blood_group_cp -> {
                bloodId = CreatePostFragment.bloods?.let {
                    CreatePostFragment.bloodsMap?.get(it.get(position))
                }!!
            }
            R.id.sp_blood_volume_cp -> {
                bloodVol = position + 1
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun onPublishClicked(): LiveData<Resource<AuthResponse>> {
        val createPostRequest = CreatePostRequest(
                problem = problem,
                blood_id = bloodId,
                date = date,
                time = time,
                phone = phone,
                volume = bloodVol,
                location = hospital,
                district_id = district.value,
                thana_id = thana.value,
                city_id = city,
                lat = 0.00,
                long = 0.923
        )
        Log.d(Companion.TAG, "onPublishClicked: ")
        return profileRepository.createPost(createPostRequest)

    }

    companion object {
        private const val TAG = "CreatePostViewModel"
    }
}