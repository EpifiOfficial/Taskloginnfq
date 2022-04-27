package com.jmartinez.taskloginnfq.repository

import com.jmartinez.taskloginnfq.network.AuthApi
import com.jmartinez.taskloginnfq.response.UserPreferences

class AuthRepository(

    private val api:AuthApi,
    private val preferences:UserPreferences
):BaseRepository() {

    suspend fun login(
        username:String,
        password:String
    )=safeApiCall{
        api.login(username,password)

    }
    suspend fun saveAuthToken(token:String,refreshToken:String){
        preferences.saveAccessTokens(token,refreshToken)
    }

}