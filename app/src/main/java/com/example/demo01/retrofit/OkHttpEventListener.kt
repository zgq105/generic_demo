package com.example.demo01.retrofit

import android.util.Log
import okhttp3.Call
import okhttp3.EventListener
import java.net.InetAddress

class OkHttpEventListener : EventListener(), EventListener.Factory {

    override fun dnsStart(call: Call, domainName: String) {
        super.dnsStart(call, domainName)
        Log.d("zgq", "dnsStart:" + call.toString() + "domainName:" + domainName)
    }

    override fun dnsEnd(call: Call, domainName: String, inetAddressList: MutableList<InetAddress>) {
        super.dnsEnd(call, domainName, inetAddressList)
        Log.d(
            "zgq",
            "dnsEnd:" + call.toString() + "domainName:" + domainName + "inetAddressList:" + inetAddressList
        )
    }

    override fun callStart(call: Call) {
        super.callStart(call)
        Log.d("zgq", "callStart:$call")
    }

    override fun callEnd(call: Call) {
        super.callEnd(call)
        Log.d("zgq", "callEnd:$call")
    }

    override fun requestBodyEnd(call: Call, byteCount: Long) {
        super.requestBodyEnd(call, byteCount)
        Log.d("zgq", "requestBodyEnd:$call,byteCount:$byteCount")
    }

    override fun create(call: Call): EventListener {
        return this
    }
}