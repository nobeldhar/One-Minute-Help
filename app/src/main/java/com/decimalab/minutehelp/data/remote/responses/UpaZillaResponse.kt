package com.decimalab.minutehelp.data.remote.responses

import com.decimalab.minutehelp.data.local.entities.District
import com.decimalab.minutehelp.data.local.entities.Upazilla
import com.google.gson.annotations.SerializedName

data class UpaZillaResponse(
        @SerializedName("code")
        val code: Int,
        @SerializedName("data")
        val `data`: List<Upazilla>,
        @SerializedName("message")
        val messages: List<String>,
        @SerializedName("status")
        val status: Boolean
) {
}