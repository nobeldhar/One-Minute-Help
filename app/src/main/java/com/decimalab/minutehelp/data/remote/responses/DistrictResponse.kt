package com.decimalab.minutehelp.data.remote.responses


import com.google.gson.annotations.SerializedName

data class DistrictResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val messages: List<String>,
    @SerializedName("status")
    val status: Boolean
) {
    data class Data(
        @SerializedName("created_at")
        val createdAt: Any,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("updated_at")
        val updatedAt: Any
    )
}