package com.decimalab.minutehelp.data.remote.requests

import com.decimalab.minutehelp.data.remote.responses.User
import com.google.gson.annotations.SerializedName

data class AuthRequest (
        @SerializedName("phone") val phone : String? = null,
        @SerializedName("password") val pass : String? = null,
        @SerializedName("name") val name : String? = null,
        @SerializedName("email") val email : String? = null,
        @SerializedName("password_confirmation") val password_confirmation : String? = null,
        @SerializedName("code") val code : String? = null
)