package com.decimalab.minutehelp.ui.profile.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.decimalab.minutehelp.data.repository.ProfileRepository
import com.decimalab.minutehelp.utils.Resource
import javax.inject.Inject

class
TimelineViewModel
@Inject constructor(
        val profileRepository: ProfileRepository
): ViewModel() {

    fun getTimeLinePosts(): LiveData<Resource<List<TimeLinePost>>> {
        return profileRepository.getTimeLinePosts()
    }
}