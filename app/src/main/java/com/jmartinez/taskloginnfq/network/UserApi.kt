package com.jmartinez.taskloginnfq.network

import com.jmartinez.taskloginnfq.response.UserResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("user")
    suspend fun getUser():UserResponse


    @POST("logout")
    suspend fun logOut():ResponseBody
}