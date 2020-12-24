package com.decimalab.minutehelp.data.remote.responses


import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.google.gson.annotations.SerializedName

data class TimeLineResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: Boolean
) {
    data class Data(
        @SerializedName("code")
        val code: Any,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("email_verified_at")
        val emailVerifiedAt: Any,
        @SerializedName("group")
        val group: Any,
        @SerializedName("group_id")
        val groupId: Any,
        @SerializedName("histories")
        val histories: List<Any>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("isDonor")
        val isDonor: Int,
        @SerializedName("isVerified")
        val isVerified: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("phone")
        val phone: String,
        @SerializedName("posts")
        val posts: List<TimeLinePost>,
        @SerializedName("role")
        val role: Any,
        @SerializedName("role_id")
        val roleId: Any,
        @SerializedName("status")
        val status: Int,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("username")
        val username: String
    )
}