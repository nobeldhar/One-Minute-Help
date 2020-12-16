package com.decimalab.minutehelp.data.remote.responses


import com.decimalab.minutehelp.data.local.entities.District
import com.google.gson.annotations.SerializedName

data class DistrictResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<District>,
    @SerializedName("message")
    val messages: List<String>,
    @SerializedName("status")
    val status: Boolean
) {

}