package com.example.demo01.retrofit

import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.internal.cache.CacheInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/888/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build()


    private fun getOkHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        //httpClientBuilder.cache(Cache())
//        httpClientBuilder.dns()
        httpClientBuilder.eventListenerFactory { OkHttpEventListener() }
        return httpClientBuilder.build()
    }




    fun getGitHubService(): GitHubService {
        return retrofit.create(GitHubService::class.java)
    }

}