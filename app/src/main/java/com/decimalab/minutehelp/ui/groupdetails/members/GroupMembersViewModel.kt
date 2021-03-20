package com.decimalab.minutehelp.ui.groupdetails.members

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.remote.responses.GroupResponse
import com.decimalab.minutehelp.data.repository.DashboardRepository
import com.decimalab.minutehelp.utils.Resource
import javax.inject.Inject

class
GroupMembersViewModel
@Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    fun getMembers(groupId: Int): LiveData<Resource<GroupResponse>> {
        return dashboardRepository.getGroupById(groupId)
    }
}