package com.decimalab.minutehelp.data.remote.responses


import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("message")
    val messages : List<String>
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
        @SerializedName("group_id")
        val groupId: Any,
        @SerializedName("id")
        val id: Int,
        @SerializedName("info")
        val info: Info,
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
        val updatedAt: String
    ) {
        data class Info(
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
            @SerializedName("date_of_birth")
            val dateOfBirth: String,
            @SerializedName("district")
            val district: District,
            @SerializedName("district_id")
            val districtId: Int,
            @SerializedName("gender")
            val gender: Gender,
            @SerializedName("id")
            val id: Int,
            @SerializedName("image")
            val image: Any,
            @SerializedName("postcode")
            val postcode: String,
            @SerializedName("thana")
            val thana: Thana,
            @SerializedName("thana_id")
            val thanaId: Int,
            @SerializedName("updated_at")
            val updatedAt: String,
            @SerializedName("user_id")
            val userId: Int
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

            data class Gender(
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
        }
    }
}