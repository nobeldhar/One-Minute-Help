package com.decimalab.minutehelp.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "timeline_post_table")
data class TimeLinePost(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,
    @SerializedName("blood")
    @ColumnInfo(name = "blood")
    val blood: Blood,
    @SerializedName("blood_id")
    @ColumnInfo(name = "blood_id")
    val bloodId: Int,
    @SerializedName("city")
    @ColumnInfo(name = "city")
    val city: City,
    @SerializedName("city_id")
    @ColumnInfo(name = "city_id")
    val cityId: Int,
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    var createdAt: String,
    @SerializedName("date")
    @ColumnInfo(name = "date")
    val date: String,
    @SerializedName("district")
    @ColumnInfo(name = "district")
    val district: District,
    @SerializedName("district_id")
    @ColumnInfo(name = "district_id")
    val districtId: Int,
    @SerializedName("group")
    @ColumnInfo(name = "group")
    val group: String?,
    @SerializedName("group_id")
    @ColumnInfo(name = "group_id")
    val groupId: Int?,
    @SerializedName("lat")
    @ColumnInfo(name = "lat")
    val lat: String,
    @SerializedName("location")
    @ColumnInfo(name = "location")
    val location: String,
    @SerializedName("long")
    @ColumnInfo(name = "long")
    val lon: String,
    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    val phone: String,
    @SerializedName("problem")
    @ColumnInfo(name = "problem")
    val problem: String,
    @SerializedName("thana")
    @ColumnInfo(name = "thana")
    val thana: Upazilla,
    @SerializedName("thana_id")
    @ColumnInfo(name = "thana_id")
    val thanaId: Int,
    @SerializedName("time")
    @ColumnInfo(name = "time")
    val time: String,
    @SerializedName("updated_at")
    @ColumnInfo(name = "updated_at")
    val updatedAt: String,
    @SerializedName("user")
    @ColumnInfo(name = "user")
    val user: User,
    @SerializedName("user_id")
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @SerializedName("volume")
    @ColumnInfo(name = "volume")
    val volume: Int,
    @SerializedName("comments_count")
    @ColumnInfo(name = "comments_count")
    val comments_count: Int,
    @SerializedName("like")
    @ColumnInfo(name = "likes")
    val likes: List<Like>,
    @SerializedName("like_count")
    @ColumnInfo(name = "like_count")
    val like_count: Int,
) {
    var isLiked:Boolean = false
    data class User(
        @SerializedName("code")
        val code: Any,
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
        @SerializedName("role")
        val role: Role,
        @SerializedName("role_id")
        val roleId: Any,
        @SerializedName("status")
        val status: Int,
        @SerializedName("username")
        val username: String
    ) {
        data class Info(
            @SerializedName("blood_id")
            val bloodId: Int,
            @SerializedName("city_id")
            val cityId: Int,
            @SerializedName("date_of_birth")
            val dateOfBirth: String,
            @SerializedName("district_id")
            val districtId: Int,
            @SerializedName("gender")
            val gender: Any,
            @SerializedName("id")
            val id: Int,
            @SerializedName("image")
            val image: String,
            @SerializedName("postcode")
            val postcode: String,
            @SerializedName("thana_id")
            val thanaId: Int,
            @SerializedName("user_id")
            val userId: Int
        )
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
    data class Like(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("post_id")
        val postId: Int,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("user_id")
        val userId: Int
    )
}