package com.decimalab.minutehelp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "upazilla_table")
data class Upazilla(
        @PrimaryKey()
        @SerializedName("id")
        @ColumnInfo(name = "id")
        val id: Int,
        @SerializedName("district_id")
        @ColumnInfo(name = "district_id")
        val districtId: Int,
        @SerializedName("name")
        @ColumnInfo(name = "name")
        val name: String
)