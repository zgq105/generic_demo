package com.example.demo01.okhttp

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class OkhttpHelper {
    fun test() {
        val client = OkHttpClient.Builder().build()
        // 创建一个 Request 对象
        val request = Request.Builder()
            .url("https://www.example.com")
            .build()

        try {
            // 使用 OkHttpClient 发起请求
            val response = client.newCall(request).execute()

            // 同步请求
            val responseData = response.body().toString()
            println(responseData)

            //异步请求
            client.newCall(request).enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val responseData = response.body().toString()
                    println(responseData)
                }

            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}