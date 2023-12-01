package com.example.demo01.leakcanary

import android.util.Log
import kotlinx.coroutines.delay
import java.lang.ref.Reference
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference

object LeakCanaryHelper {

    private const val TAG = "LeakCanaryHelper"

    data class Person(val name: String)

    suspend fun testReferenceQueueUsage() {
        val referenceQueue = ReferenceQueue<Person>()
        val person = WeakReference(Person("zgq"), referenceQueue)
        //手动触发gc
        System.gc()
        delay(3000)

        val result = referenceQueue.poll()
        Log.d(TAG, "result:$result")
        if (result != null) {
            Log.d(TAG, "Object has been collected")
        } else {
            Log.d(TAG, "Object has not been collected")
        }
    }
}