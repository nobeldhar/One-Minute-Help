package com.decimalab.minutehelp.data.remote.responses

import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.google.gson.annotations.SerializedName

data class TimeLineResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<TimeLinePost>,
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: Boolean
) {

}