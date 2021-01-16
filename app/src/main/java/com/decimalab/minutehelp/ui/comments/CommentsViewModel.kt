package com.decimalab.minutehelp.ui.comments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.remote.responses.CommentsResponse
import com.decimalab.minutehelp.data.repository.DashboardRepository
import com.decimalab.minutehelp.data.repository.ProfileRepository
import com.decimalab.minutehelp.utils.Resource
import javax.inject.Inject

class
CommentsViewModel
@Inject constructor(
    val dashboardRepository: DashboardRepository
): ViewModel() {
    var comment: String? = null

    fun commentOnPost(postId:Int): LiveData<Resource<AuthResponse>>? {
        return comment?.let {
            Log.d(Companion.TAG, "commentOnPost: called")
            dashboardRepository.commentOnPost(it, postId) }
    }

    fun getComments(postId: Int): LiveData<Resource<CommentsResponse>> {
        return dashboardRepository.getComments(postId)
    }

    companion object {
        private const val TAG = "CommentsViewModel"
    }
}