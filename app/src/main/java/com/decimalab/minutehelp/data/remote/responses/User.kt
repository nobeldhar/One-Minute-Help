package com.decimalab.minutehelp.data.remote.responses
import com.google.gson.annotations.SerializedName

data class User(

	@SerializedName("id") val id: Int,
	@SerializedName("name") val name: String,
	@SerializedName("email") val email: String,
	@SerializedName("phone") val phone: String,
	@SerializedName("code") val code: Int?,
	@SerializedName("isVerified") val isVerified: Int,
	@SerializedName("email_verified_at") val email_verified_at: String?,
	@SerializedName("group_id") val group_id: String?,
	@SerializedName("image") val image: String?,
	@SerializedName("role_id") val role_id: String?,
	@SerializedName("status") val status: Int?,
	@SerializedName("created_at") val created_at: String?,
	@SerializedName("updated_at") val updated_at: String?
) {
	constructor(id: Int, name: String, email: String, phone: String, isVerified: Int) : this(id,name,email, phone, null, isVerified, null, null, null, null, null, null, null  )
	constructor(id: Int, name: String, email: String, phone: String, isVerified: Int, image: String?) : this(id,name,email, phone, null, isVerified, null, null, image, null, null, null, null  )

}