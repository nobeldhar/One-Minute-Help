package com.decimalab.minutehelp.data.remote.responses


import com.decimalab.minutehelp.data.local.entities.Group
import com.google.gson.annotations.SerializedName

data class GroupsResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<Group>,
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: Boolean
)