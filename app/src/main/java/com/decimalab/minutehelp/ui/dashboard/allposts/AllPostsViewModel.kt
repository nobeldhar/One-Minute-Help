package com.decimalab.minutehelp.ui.dashboard.allposts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.local.entities.Post
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.repository.DashboardRepository
import com.decimalab.minutehelp.utils.Resource
import javax.inject.Inject

class
AllPostsViewModel
@Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    fun getPosts(): LiveData<Resource<List<Post>>> {
        return dashboardRepository.getPosts()
    }

    fun likePost(post: Post): LiveData<Resource<AuthResponse>> {
        return dashboardRepository.likePost(post.id)
    }
}