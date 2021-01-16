package com.decimalab.minutehelp.data.local.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
data class Comment (
    @SerializedName("childs")
    val childs: List<Child>,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("created_at")
    val createdAt: String,
    @PrimaryKey()
    @SerializedName("id")
    val id: Int,
    @SerializedName("p_id")
    val pId: Int,
    @SerializedName("post_id")
    val postId: Int,
    @SerializedName("reply_id")
    val replyId: Any,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: TimeLinePost.User,
    @SerializedName("user_id")
    val userId: Int
) : Parcelable{
    var expanded: Boolean = false

    constructor(parcel: Parcel) : this(
        TODO("childs"),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        TODO("replyId"),
        parcel.readString().toString(),
        TODO("user"),
        parcel.readInt()
    ) {
        expanded = parcel.readByte() != 0.toByte()
    }

    data class Child(
        @SerializedName("comment")
        val comment: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("p_id")
        val pId: Int,
        @SerializedName("post_id")
        val postId: Int,
        @SerializedName("reply_id")
        val replyId: Int,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("user")
        val user: TimeLinePost.User,
        @SerializedName("user_id")
        val userId: Int
    ): Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString().toString(),
            TODO("user"),
            parcel.readInt()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(comment)
            parcel.writeString(createdAt)
            parcel.writeInt(id)
            parcel.writeInt(pId)
            parcel.writeInt(postId)
            parcel.writeInt(replyId)
            parcel.writeString(updatedAt)
            parcel.writeInt(userId)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Child> {
            override fun createFromParcel(parcel: Parcel): Child {
                return Child(parcel)
            }

            override fun newArray(size: Int): Array<Child?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(comment)
        parcel.writeString(createdAt)
        parcel.writeInt(id)
        parcel.writeInt(pId)
        parcel.writeInt(postId)
        parcel.writeString(updatedAt)
        parcel.writeInt(userId)
        parcel.writeByte(if (expanded) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comment> {
        override fun createFromParcel(parcel: Parcel): Comment {
            return Comment(parcel)
        }

        override fun newArray(size: Int): Array<Comment?> {
            return arrayOfNulls(size)
        }
    }


}