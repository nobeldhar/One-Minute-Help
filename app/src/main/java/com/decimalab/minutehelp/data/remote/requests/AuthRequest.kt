package com.decimalab.minutehelp.data.remote.requests

import com.google.gson.annotations.SerializedName

data class AuthRequest (
        @SerializedName("phone") val phone : String,
        @SerializedName("password") val pass : String,
        @SerializedName("name") val name : String? = null,
        @SerializedName("email") val email : String? = null,
        @SerializedName("password_confirmation") val password_confirmation : String? = null
)