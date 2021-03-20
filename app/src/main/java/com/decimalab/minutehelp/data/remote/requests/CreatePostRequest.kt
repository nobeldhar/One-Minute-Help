package com.decimalab.minutehelp.data.remote.requests

import com.google.gson.annotations.SerializedName

class CreatePostRequest(
        @SerializedName("group_id") val group_id: Int? = null,
        @SerializedName("problem") val problem: String? = null,
        @SerializedName("blood_id") val blood_id: Int? = null,
        @SerializedName("date") val date: String? = null,
        @SerializedName("time") val time: String? = null,
        @SerializedName("phone") val phone: String? = null,
        @SerializedName("volume") val volume: Int? = null,
        @SerializedName("location") val location: String? = null,
        @SerializedName("district_id") val district_id: Int? = null,
        @SerializedName("thana_id") val thana_id: Int? = null,
        @SerializedName("city_id") val city_id: Int? = null,
        @SerializedName("long") val long: Double? = null,
        @SerializedName("lat") val lat: Double? = null
) {

}