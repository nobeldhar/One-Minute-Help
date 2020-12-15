package com.decimalab.minutehelp.data.remote.responses


import com.google.gson.annotations.SerializedName

data class BloodResponse(
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
        @SerializedName("blood")
        val blood: String,
        @SerializedName("created_at")
        val createdAt: Any,
        @SerializedName("id")
        val id: Int,
        @SerializedName("slug")
        val slug: String,
        @SerializedName("updated_at")
        val updatedAt: Any
    )
}