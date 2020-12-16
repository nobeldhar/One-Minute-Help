package com.decimalab.minutehelp.data.remote.responses

import com.decimalab.minutehelp.data.local.entities.City
import com.decimalab.minutehelp.data.local.entities.District
import com.google.gson.annotations.SerializedName

data class CityResponse (
        @SerializedName("code")
        val code: Int,
        @SerializedName("data")
        val `data`: List<City>,
        @SerializedName("message")
        val messages: List<String>,
        @SerializedName("status")
        val status: Boolean
){
}