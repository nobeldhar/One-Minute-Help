package com.decimalab.minutehelp.data.remote.responses


import com.decimalab.minutehelp.data.local.entities.Comment
import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.google.gson.annotations.SerializedName

data class CommentsResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("comments")
    val comments: List<com.decimalab.minutehelp.data.local.entities.Comment>,
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("post")
    val post: TimeLinePost,
    @SerializedName("status")
    val status: Boolean
)