package com.decimalab.minutehelp.data.remote.responses


import com.decimalab.minutehelp.data.local.entities.Blood
import com.google.gson.annotations.SerializedName

data class TimeLineResponse2(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: Boolean
) {
    data class Data(
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
        @SerializedName("group")
        val group: String,
        @SerializedName("group_id")
        val groupId: Any,
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
            val groupId: Any,
            @SerializedName("id")
            val id: Int,
            @SerializedName("info")
            val info: Info,
            @SerializedName("isDonor")
            val isDonor: Int,
            @SerializedName("isVerified")
            val isVerified: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("phone")
            val phone: String,
            @SerializedName("role_id")
            val roleId: Any,
            @SerializedName("status")
            val status: Int,
            @SerializedName("updated_at")
            val updatedAt: String,
            @SerializedName("username")
            val username: String
        ) {
            data class Info(
                @SerializedName("blood_id")
                val bloodId: Int,
                @SerializedName("city_id")
                val cityId: Int,
                @SerializedName("created_at")
                val createdAt: String,
                @SerializedName("date_of_birth")
                val dateOfBirth: String,
                @SerializedName("district_id")
                val districtId: Int,
                @SerializedName("gender")
                val gender: Int,
                @SerializedName("id")
                val id: Int,
                @SerializedName("image")
                val image: String,
                @SerializedName("postcode")
                val postcode: String,
                @SerializedName("thana_id")
                val thanaId: Int,
                @SerializedName("updated_at")
                val updatedAt: String,
                @SerializedName("user_id")
                val userId: Int,
                @SerializedName("blood")
                val blood: com.decimalab.minutehelp.data.local.entities.Blood
            )
        }
    }
}