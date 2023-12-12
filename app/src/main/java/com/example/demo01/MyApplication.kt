package com.example.demo01

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.demo01.plug_in.HookHelper

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        try {
            HookHelper.hookInstrumentation(base)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
