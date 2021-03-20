package com.decimalab.minutehelp.ui.groupdetails.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.local.entities.Post
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.remote.responses.GroupResponse
import com.decimalab.minutehelp.data.repository.DashboardRepository
import com.decimalab.minutehelp.utils.Resource
import javax.inject.Inject

class GroupTimelineViewModel
@Inject constructor(
    private val dashboardRepository: DashboardRepository
): ViewModel() {

    fun getPosts(groupId: Int): LiveData<Resource<GroupResponse>> {
        return dashboardRepository.getGroupById(groupId)
    }

    fun likePost(post: Post): LiveData<Resource<AuthResponse>> {
        return dashboardRepository.likePost(post.id)
    }
}