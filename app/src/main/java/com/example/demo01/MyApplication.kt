package com.example.demo01

import android.app.Application
import android.content.Context
import android.os.StrictMode
import android.util.Log
import com.example.demo01.plug_in.HookHelper

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("zgq", "MyApplication-onCreate:$this")
        if (BuildConfig.DEBUG) {
            enableStrictMode()
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        try {
            HookHelper.hookInstrumentation(base)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }
}
