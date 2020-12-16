package com.decimalab.minutehelp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "district_table")
class District(
        @PrimaryKey()
        @SerializedName("id")
        @ColumnInfo(name = "id")
        val id: Int,
        @SerializedName("name")
        @ColumnInfo(name = "name")
        val name: String
        ) {
}