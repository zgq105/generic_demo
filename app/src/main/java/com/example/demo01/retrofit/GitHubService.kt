package com.example.demo01.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>

    @GET("users/{user}/repos")
    suspend fun listRepos2(@Path("user") user: String): List<Repo>
}