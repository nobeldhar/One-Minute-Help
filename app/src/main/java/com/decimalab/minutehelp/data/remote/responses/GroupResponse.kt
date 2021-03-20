package com.decimalab.minutehelp.data.remote.responses


import com.decimalab.minutehelp.data.local.entities.Group
import com.decimalab.minutehelp.data.local.entities.Post
import com.google.gson.annotations.SerializedName

data class GroupResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Group,
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("posts")
    val posts: List<com.decimalab.minutehelp.data.local.entities.Post>,
    @SerializedName("status")
    val status: Boolean
) {

}