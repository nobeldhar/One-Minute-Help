package com.decimalab.minutehelp.data.remote.responses
import com.google.gson.annotations.SerializedName

data class User (

		@SerializedName("id") val id : Int,
		@SerializedName("name") val name : String,
		@SerializedName("email") val email : String,
		@SerializedName("phone") val phone : Int,
		@SerializedName("code") val code : Int,
		@SerializedName("isVerified") val isVerified : Int,
		@SerializedName("email_verified_at") val email_verified_at : String,
		@SerializedName("group_id") val group_id : String,
		@SerializedName("role_id") val role_id : String,
		@SerializedName("status") val status : Int,
		@SerializedName("created_at") val created_at : String,
		@SerializedName("updated_at") val updated_at : String
)