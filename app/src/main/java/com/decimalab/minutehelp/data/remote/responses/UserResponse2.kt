package com.decimalab.minutehelp.data.remote.responses


import com.decimalab.minutehelp.data.local.entities.Group
import com.decimalab.minutehelp.data.local.entities.History
import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.google.gson.annotations.SerializedName

data class UserResponse2(
    @SerializedName("auto_image")
    val autoImage: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: Data,
    @SerializedName("image")
    val image: String,
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: Boolean
) {
    data class Data(
        @SerializedName("code")
        val code: Int,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("email_verified_at")
        val emailVerifiedAt: String,
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
        val role: TimeLinePost.User.Role,
        @SerializedName("role_id")
        val roleId: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("username")
        val username: String
    )
}