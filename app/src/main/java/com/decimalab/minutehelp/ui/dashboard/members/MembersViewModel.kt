package com.decimalab.minutehelp.ui.dashboard.members

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.local.entities.Group
import com.decimalab.minutehelp.data.repository.DashboardRepository
import com.decimalab.minutehelp.utils.Resource
import javax.inject.Inject

class
MembersViewModel
@Inject constructor(
    private val dashboardRepository: DashboardRepository
): ViewModel() {
    fun getGroups(): LiveData<Resource<List<Group>>> {
        return dashboardRepository.getGroups()
    }
    // TODO: Implement the ViewModel
}