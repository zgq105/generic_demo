package com.example.demo01.network

import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.net.NetworkCapabilities
import android.os.Build
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi

object NetworkStatsManagerHelper {

    fun getNetworkStats(context:Context) {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val subscriberId = telephonyManager.subscriberId
        val networkStatsManager = context.getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager
        val bucket = NetworkStats.Bucket()
        var networkStats: NetworkStats? = null
        val stime = System.currentTimeMillis()
        val etime = System.currentTimeMillis()
        try {
            networkStats = networkStatsManager.querySummary(
                NetworkCapabilities.TRANSPORT_CELLULAR,
                subscriberId, stime, etime
            )
        } catch (e: Exception) {

        }
        //遍历时间桶
        while (networkStats!=null && networkStats.hasNextBucket()){

        }

    }
}