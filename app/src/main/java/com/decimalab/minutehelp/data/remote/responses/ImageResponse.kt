package com.decimalab.minutehelp.data.remote.responses

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("image")
    val image: String?,
    @SerializedName("auto_image")
    val auto_image: String,
    @SerializedName("message")
    val messages: List<String>,
    @SerializedName("status")
    val status: Boolean
) {
}