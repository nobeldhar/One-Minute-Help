package com.decimalab.minutehelp.data.remote.requests

import com.google.gson.annotations.SerializedName

class SettingsRequest(
        @SerializedName("name") val name: String? = null,
        @SerializedName("email") val email: String? = null,
        @SerializedName("blood_id") val blood_id: Int? = null,
        @SerializedName("date_of_birth") val date_of_birth: String? = null,
        @SerializedName("gender") val gender: Int? = null,
        @SerializedName("district_id") val district_id: Int? = null,
        @SerializedName("thana_id") val thana_id: Int? = null,
        @SerializedName("city_id") val city_id: Int? = null,
        @SerializedName("postcode") val postcode: String? = null,
        @SerializedName("current_password") val current_password: String? = null,
        @SerializedName("password") val password: String? = null,
        @SerializedName("password_confirmation") val password_confirmation: String? = null

) {
}