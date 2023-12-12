package com.example.demo01.retrofit

import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.internal.cache.CacheInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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




    private fun getGitHubService(): GitHubService {
        return retrofit.create(GitHubService::class.java)
    }

    fun test() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val call = getGitHubService().listRepos("octocat")
                //同步请求
                Log.d("zgq",call.execute().body().toString())
                //异步请求
                call.enqueue(object : Callback<List<Repo>> {
                    override fun onResponse(
                        call: Call<List<Repo>>,
                        response: Response<List<Repo>>
                    ) {

                    }

                    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

                    }
                })
            }
            //挂起函数写法
            //val list = RetrofitHelper.getGitHubService().listRepos2("octocat")
        }
    }

}