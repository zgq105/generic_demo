package com.example.demo01.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyService1 : Service() {
    private val binder = MyBinder()

    inner class MyBinder : Binder() {
        fun getService(): MyService1 = this@MyService1
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    // Service method
    fun getHelloMessage(): String {
        return "Hello from the service!"
    }

    fun sendBroadcast() {
        val action = "com.example.ACTION_FROM_SERVICE"
        sendBroadcast(Intent(action).apply {
            putExtra("data","hello world")
        })
    }
}
