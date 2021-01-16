package com.decimalab.minutehelp.data.remote.services

import com.decimalab.minutehelp.data.remote.responses.AuthResponse
import com.decimalab.minutehelp.data.remote.responses.CommentsResponse
import retrofit2.Response
import retrofit2.http.*

interface DashboardService {

    @GET("auth/verify-login")
    suspend fun verifyAuthToken(): Response<AuthResponse>
    @FormUrlEncoded
    @POST("auth/posts/comments/store")
    suspend fun commentOnPost(@Field("comment") comment: String,
                              @Field("post_id") postId: Int,
                              @Field("p_id") p_id: Int? = null,
                              @Field("reply_id ") reply_id : Int? = null):
            Response<AuthResponse>
    @GET("auth/posts/comments/{id}")
    suspend fun getComments(@Path("id") id: Int):
            Response<CommentsResponse>

}