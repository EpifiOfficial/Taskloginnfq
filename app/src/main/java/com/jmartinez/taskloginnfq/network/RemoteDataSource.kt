package com.jmartinez.taskloginnfq.network

import com.jmartinez.taskloginnfq.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RemoteDataSource {

    companion object{
        private const val BASE_URL = "https://vidqjclbhmef.herokuapp.com/"
    }


    fun <API>buildApi(
        api: Class<API>,
        authToken:String? = null
    ):API{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor {chain ->  
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Authorization","Bearer $authToken")
                }.build())
            }.also { client->

                if (BuildConfig.DEBUG){
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
                }

            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}