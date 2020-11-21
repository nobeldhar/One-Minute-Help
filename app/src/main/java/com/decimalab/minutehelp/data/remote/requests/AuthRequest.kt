package com.decimalab.minutehelp.data.remote.requests

import com.decimalab.minutehelp.data.remote.responses.User
import com.google.gson.annotations.SerializedName

data class AuthRequest (
        @SerializedName("phone") val phone : String,
        @SerializedName("password") val pass : String
)