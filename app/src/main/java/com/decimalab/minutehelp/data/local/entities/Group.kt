package com.decimalab.minutehelp.data.local.entities

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("city")
    val city: City,
    @SerializedName("city_id")
    val cityId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("district")
    val district: District,
    @SerializedName("district_id")
    val districtId: Int,
    @SerializedName("fb_name")
    val fbName: String,
    @SerializedName("fb_url")
    val fbUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("post")
    val post: List<Post>,
    @SerializedName("postcode")
    val postcode: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("thana")
    val thana: Thana,
    @SerializedName("thana_id")
    val thanaId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("users")
    val users: List<User>,
    @SerializedName("website")
    val website: Any
) {
    data class City(
        @SerializedName("created_at")
        val createdAt: Any,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("thana_id")
        val thanaId: Int,
        @SerializedName("updated_at")
        val updatedAt: Any
    )

    data class District(
        @SerializedName("created_at")
        val createdAt: Any,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("updated_at")
        val updatedAt: Any
    )

    data class Post(
        @SerializedName("blood")
        val blood: Blood,
        @SerializedName("blood_id")
        val bloodId: Int,
        @SerializedName("city")
        val city: City,
        @SerializedName("city_id")
        val cityId: Int,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("district")
        val district: District,
        @SerializedName("district_id")
        val districtId: Int,
        @SerializedName("group_id")
        val groupId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("lat")
        val lat: String,
        @SerializedName("location")
        val location: String,
        @SerializedName("long")
        val long: String,
        @SerializedName("phone")
        val phone: String,
        @SerializedName("problem")
        val problem: String,
        @SerializedName("thana")
        val thana: Thana,
        @SerializedName("thana_id")
        val thanaId: Int,
        @SerializedName("time")
        val time: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("user")
        val user: User,
        @SerializedName("user_id")
        val userId: Int,
        @SerializedName("volume")
        val volume: Int
    ) {
        data class Blood(
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

        data class City(
            @SerializedName("created_at")
            val createdAt: Any,
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("thana_id")
            val thanaId: Int,
            @SerializedName("updated_at")
            val updatedAt: Any
        )

        data class District(
            @SerializedName("created_at")
            val createdAt: Any,
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("updated_at")
            val updatedAt: Any
        )

        data class Thana(
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("district_id")
            val districtId: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("updated_at")
            val updatedAt: String
        )

        data class User(
            @SerializedName("code")
            val code: Any,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("email")
            val email: String,
            @SerializedName("email_verified_at")
            val emailVerifiedAt: Any,
            @SerializedName("group_id")
            val groupId: Int,
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

    data class Thana(
        @SerializedName("created_at")
        val createdAt: Any,
        @SerializedName("district_id")
        val districtId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("updated_at")
        val updatedAt: Any
    )

    data class User(
        @SerializedName("code")
        val code: Any,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("email_verified_at")
        val emailVerifiedAt: Any,
        @SerializedName("group_id")
        val groupId: Int,
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