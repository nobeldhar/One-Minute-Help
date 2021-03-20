package com.decimalab.minutehelp.ui.groupdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.remote.responses.GroupResponse
import com.decimalab.minutehelp.data.remote.responses.UserResponse3
import com.decimalab.minutehelp.data.repository.DashboardRepository
import com.decimalab.minutehelp.utils.Resource
import javax.inject.Inject

class
GroupDetailsViewModel
@Inject constructor(
    private val dashboardRepository: DashboardRepository
): ViewModel() {

    fun getGroupById(id: Int): LiveData<Resource<GroupResponse>> {
        return dashboardRepository.getGroupById(id)
    }

    fun getUser(): LiveData<Resource<UserResponse3>> {
        return dashboardRepository.getUser()
    }
}