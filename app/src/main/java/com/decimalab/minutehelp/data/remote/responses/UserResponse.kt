package com.decimalab.minutehelp.data.remote.responses


import com.decimalab.minutehelp.data.local.entities.Group
import com.decimalab.minutehelp.data.local.entities.History
import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("donner")
    val donner: Donner,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any,
    @SerializedName("group")
    val group: Group,
    @SerializedName("group_id")
    val groupId: Int,
    @SerializedName("histories")
    val histories: List<History>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("info")
    val info: TimeLinePost.User.Info,
    @SerializedName("isVerified")
    val isVerified: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("posts")
    val posts: List<TimeLinePost>,
    @SerializedName("role")
    val role: TimeLinePost.User.Role,
    @SerializedName("role_id")
    val roleId: Int,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("updated_at")
    val updatedAt: String
) {
    data class Donner(
        @SerializedName("created_at")
        val createdAt: Any,
        @SerializedName("id")
        val id: Int,
        @SerializedName("updated_at")
        val updatedAt: Any,
        @SerializedName("user_id")
        val userId: Int
    )

}