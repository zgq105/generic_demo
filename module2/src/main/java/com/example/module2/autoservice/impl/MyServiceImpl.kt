package com.example.module2.autoservice.impl

import android.util.Log
import com.example.common.IMyService
import com.google.auto.service.AutoService

@AutoService(IMyService::class)
class MyServiceImpl : IMyService {

    override fun test() {
        Log.d(IMyService.TAG, "test")
    }
}