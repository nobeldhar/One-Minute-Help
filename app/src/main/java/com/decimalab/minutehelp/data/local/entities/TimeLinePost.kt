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
        val createdAt: String,
        @SerializedName("date")
        @ColumnInfo(name = "date")
        val date: String,
        @SerializedName("district")
        @ColumnInfo(name = "district")
        val district: District,
        @SerializedName("district_id")
        @ColumnInfo(name = "district_id")
        val districtId: Int,
        @SerializedName("group_id")
        @ColumnInfo(name = "group_id")
        val groupId: Int,
        @SerializedName("location")
        @ColumnInfo(name = "location")
        val location: String,
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
        @SerializedName("user_id")
        @ColumnInfo(name = "user_id")
        val userId: Int,
        @SerializedName("volume")
        @ColumnInfo(name = "volume")
        val volume: Int
) {
        var dateNTime: String? = null

}