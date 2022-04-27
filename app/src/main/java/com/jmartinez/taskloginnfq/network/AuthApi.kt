package com.jmartinez.taskloginnfq.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST
    fun login(
        @Field("username") username :String,
        @Field("password") password:String
    ):Any


}