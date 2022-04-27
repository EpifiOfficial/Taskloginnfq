package com.jmartinez.taskloginnfq.repository

import com.jmartinez.taskloginnfq.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend ()-> T
    ):Resource<T>{
        return withContext(Dispatchers.IO){
            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable:Throwable){
                when(throwable){
                    is HttpException -> {
                        Resource.Failure(false,throwable.code(), throwable.response()?.errorBody())
                    }
                    else ->
                        Resource.Failure(true,null ,null)

                }
            }
        }

    }
}