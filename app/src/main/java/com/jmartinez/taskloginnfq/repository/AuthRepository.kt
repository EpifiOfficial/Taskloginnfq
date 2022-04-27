package com.jmartinez.taskloginnfq.repository

import com.jmartinez.taskloginnfq.network.AuthApi

class AuthRepository(

    private val api:AuthApi
):BaseRepository() {

    suspend fun login(
        username:String,
        password:String
    )=safeApiCall{
        api.login(username,password)

    }

}