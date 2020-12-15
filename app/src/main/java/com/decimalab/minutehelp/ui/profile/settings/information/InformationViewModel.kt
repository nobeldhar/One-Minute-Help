package com.decimalab.minutehelp.ui.profile.settings.information

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.R
import com.decimalab.minutehelp.data.remote.requests.SettingsRequest
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.remote.responses.BloodResponse
import com.decimalab.minutehelp.data.repository.SettingsRepository
import com.decimalab.minutehelp.utils.Resource
import kotlinx.android.synthetic.main.fragment_information.view.*
import java.util.*
import javax.inject.Inject

class
InformationViewModel
@Inject constructor(
        val settingsRepository: SettingsRepository
) : ViewModel(), AdapterView.OnItemSelectedListener {

    val cldr: Calendar = Calendar.getInstance();
    val day = cldr.get(Calendar.DAY_OF_MONTH);
    val month = cldr.get(Calendar.MONTH);
    val year = cldr.get(Calendar.YEAR);
    var bloodGroup: Int = 1
    var gender: Int = 1
    var dateOfBirth = "$year/${month+1}/$day"
    val uiError = MutableLiveData<String>()

    fun onUpdateClicked(){
        Log.d(Companion.TAG, "onUpdateClicked: ")
        //uiError.value = "$bloodGroup Selected\n Date of Birth: $dateOfBirth\nGender : $gender"
        uiError.value = "OK"
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(TAG, "onItemSelected: ")
        when(parent?.id){
            R.id.sp_gender -> {
                Log.d(TAG, "onItemSelected: sp_gender")
                gender = position + 1
            }
            R.id.sp_blood_group -> {
                Log.d(TAG, "onItemSelected: sp_bloodGroup")
                bloodGroup = position + 1
            }
        }

    }

    fun getBloodList(): LiveData<Resource<BloodResponse>> {
        return settingsRepository.getBloodList()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun update(): LiveData<Resource<AuthResponse>> {

        val settingsRequest = SettingsRequest(blood_id = bloodGroup, date_of_birth = dateOfBirth, gender = gender)
        return settingsRepository.updateOtherInfo(settingsRequest)
    }

    // TODO: Implement the ViewModel
    companion object {
        private const val TAG = "InformationViewModel"
    }
}