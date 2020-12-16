package com.decimalab.minutehelp.data.local.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "city_table")
data class City(
        @PrimaryKey()
        @SerializedName("id")
        @ColumnInfo(name = "id")
        val id: Int,
        @SerializedName("name")
        @ColumnInfo(name = "name")
        val name: String,
        @SerializedName("thana_id")
        @ColumnInfo(name = "thana_id")
        val thanaId: Int
)