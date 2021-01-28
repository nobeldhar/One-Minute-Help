package com.decimalab.minutehelp.data.local.entities

import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("created_at")
    val createdAt: Any,
    @SerializedName("donate_date")
    val donateDate: String,
    @SerializedName("hospital_name")
    val hospitalName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("updated_at")
    val updatedAt: Any,
    @SerializedName("user_id")
    val userId: Int
)