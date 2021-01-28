package com.decimalab.minutehelp.data.remote.responses

import com.decimalab.minutehelp.data.local.entities.Post
import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<Post>,
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: Boolean
) {

}