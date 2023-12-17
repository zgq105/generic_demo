package com.example.demo01.leakcanary

import android.util.Log
import kotlinx.coroutines.delay
import java.io.SequenceInputStream
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference
import java.util.WeakHashMap


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

    data class MyObject(val data: String)

    suspend fun testWeakReference() {
        var myObject: MyObject? = MyObject("Hello, WeakReference!")
        val weakRef = WeakReference(myObject)
        // 输出对象的数据
        Log.d(TAG,"Data from object: " + myObject?.data)
        Log.d(TAG,"Data from weak reference: " + weakRef.get()?.data)

        // 执行垃圾回收
        System.gc()
        delay(4000)
        Log.d(TAG,"Data from weak reference after GC: " + weakRef.get()?.data)

        val weakHashMap = WeakHashMap<String,String>()
        weakHashMap["1"] = "2"


    }
}