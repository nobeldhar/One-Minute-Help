package com.decimalab.minutehelp.data.remote.requests

import com.google.gson.annotations.SerializedName

class SettingsRequest(
        @SerializedName("name") val name: String? = null,
        @SerializedName("email") val email: String? = null,
        @SerializedName("blood_id") val blood_id: Int? = null,
        @SerializedName("date_of_birth") val date_of_birth: String? = null,
        @SerializedName("gender") val gender_id: Int? = null,
        @SerializedName("image") val image: String? = null,
        @SerializedName("district_id") val district_id: Int? = null,
        @SerializedName("thana_id") val thana_id: Int? = null,
        @SerializedName("city_id") val city_id: Int? = null,
        @SerializedName("blood") val blood: String? = null,
        @SerializedName("gendert") val gender: String? = null,
        @SerializedName("district") val district: String? = null,
        @SerializedName("thana") val thana: String? = null,
        @SerializedName("city") val city: String? = null,
        @SerializedName("postcode") val postcode: String? = null,
        @SerializedName("current_password") val current_password: String? = null,
        @SerializedName("password") val password: String? = null,
        @SerializedName("password_confirmation") val password_confirmation: String? = null

) {
}