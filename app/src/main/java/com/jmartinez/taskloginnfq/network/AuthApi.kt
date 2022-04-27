package com.jmartinez.taskloginnfq.network

import com.jmartinez.taskloginnfq.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("credentials")
    suspend fun login(
        @Field("username") username :String,
        @Field("password") password:String
    ):LoginResponse


}