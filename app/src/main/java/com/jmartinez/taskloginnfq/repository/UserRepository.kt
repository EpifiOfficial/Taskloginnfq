package com.jmartinez.taskloginnfq.repository

import com.jmartinez.taskloginnfq.network.AuthApi
import com.jmartinez.taskloginnfq.network.UserApi
import com.jmartinez.taskloginnfq.response.UserPreferences

class UserRepository(

    private val api:UserApi,
):BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}