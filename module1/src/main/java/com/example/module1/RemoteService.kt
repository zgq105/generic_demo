package com.example.module1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RemoteService : Service() {


    private val binder = object : IMyAidlInterface.Stub() {
        private var response: IResponse? = null
        override fun setData(a: Int) {
            Log.d("zgq", "aidl server get client data:$a")
        }

        override fun getData(): Int {
            return 1103
        }

        override fun registerCallBack(response: IResponse?) {
            this.response = response
        }

        override fun addPerson(p: Person?) {
            Log.d("zgq", "aidl server get client data:${p?.toString()}")

            //模拟服务端异步调用情况
            GlobalScope.launch {
                delay(3000)
                withContext(Dispatchers.Main) {
                    Log.d("zgq", "callBack?.onSuccess:$response")
                    response?.notifySuccess("异步调用成功")
                }
            }
        }

    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}