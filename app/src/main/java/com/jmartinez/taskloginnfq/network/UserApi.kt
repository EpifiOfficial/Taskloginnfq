package com.jmartinez.taskloginnfq.network

import com.jmartinez.taskloginnfq.response.UserResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUser():UserResponse
}