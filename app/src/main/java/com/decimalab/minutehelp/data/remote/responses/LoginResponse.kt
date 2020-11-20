import com.google.gson.annotations.SerializedName



data class LoginResponse (

		@SerializedName("status") val status : Boolean,
		@SerializedName("code") val code : Int,
		@SerializedName("data") val data : User,
		@SerializedName("access_token") val access_token : String,
		@SerializedName("token_type") val token_type : String,
		@SerializedName("expires_at") val expires_at : String,
		@SerializedName("message") val messages : List<String>
)