package com.decimalab.minutehelp.data.remote.responses


import com.decimalab.minutehelp.data.local.entities.Group
import com.decimalab.minutehelp.data.local.entities.Post
import com.decimalab.minutehelp.data.local.entities.TimeLinePost
import com.google.gson.annotations.SerializedName

data class UserResponse3(
    @SerializedName("auto_image")
    val autoImage: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("image")
    val image: Any,
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("posts")
    val posts: List<TimeLinePost>,
    @SerializedName("status")
    val status: Boolean
) {
    data class Data(
        @SerializedName("code")
        val code: Any,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("email")
        val email: Any,
        @SerializedName("email_verified_at")
        val emailVerifiedAt: Any,
        @SerializedName("group")
        val group: Group?,
        @SerializedName("group_id")
        val groupId: Int,
        @SerializedName("histories")
        val histories: List<Any>,
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
        @SerializedName("role")
        val role: Role,
        @SerializedName("role_id")
        val roleId: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("username")
        val username: String
    ) {
        data class Role(
            @SerializedName("created_at")
            val createdAt: Any,
            @SerializedName("id")
            val id: Int,
            @SerializedName("role")
            val role: String,
            @SerializedName("slug")
            val slug: String,
            @SerializedName("updated_at")
            val updatedAt: Any
        )
    }


}