package com.decimalab.minutehelp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.decimalab.minutehelp.data.remote.responses.TimeLineResponse2
import com.decimalab.minutehelp.data.remote.responses.User
import com.google.gson.annotations.SerializedName
@Entity(tableName = "group_table")
data class Group(
    @SerializedName("city")
    val city: City?,
    @SerializedName("city_id")
    val cityId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("district")
    val district: District?,
    @SerializedName("district_id")
    val districtId: Int,
    @SerializedName("fb_name")
    val fbName: String,
    @SerializedName("fb_url")
    val fbUrl: String,
    @PrimaryKey()
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    /*@SerializedName("post")
    val posts: List<TimeLinePost>?,*/
    @SerializedName("postcode")
    val postcode: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("thana")
    val thana: Upazilla?,
    @SerializedName("thana_id")
    val thanaId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("users")
    val users: List<TimeLinePost.User>,
    @SerializedName("website")
    val website: String?
)