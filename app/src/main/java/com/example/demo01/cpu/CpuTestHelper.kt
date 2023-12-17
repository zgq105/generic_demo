package com.example.demo01.cpu

import android.util.Log

object CpuTestHelper {
    private const val TAG = "CpuTestHelper"
    fun cpuTest() {
        //模拟复杂计算导致cpu负载过高
        for (i in 0..20000) {
            Log.d(TAG, i.toString())
        }
    }

    var i = 0
    fun cpuTest2() {
        //递归测试CPU
        Log.d(TAG, "cpuTest2")
        if (i > 10000) {
            return
        } else {
            i++
            cpuTest2()
        }
    }

    fun cpuTest3() {
        Log.d(TAG, "cpuTest3")
    }

}