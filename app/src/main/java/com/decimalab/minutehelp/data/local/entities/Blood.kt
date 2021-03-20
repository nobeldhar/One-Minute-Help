package com.decimalab.minutehelp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Blood(
        @PrimaryKey
        @SerializedName("id")
        @ColumnInfo(name = "id")
        val id: Int?,
        @SerializedName("slug")
        @ColumnInfo(name = "slug")
        val slug: String?,
        @SerializedName("blood")
        @ColumnInfo(name = "blood")
        val blood: String?
)