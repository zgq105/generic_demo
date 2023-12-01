package com.example.demo01

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.util.Log

class MyHandlerThread(name: String) : HandlerThread(name) {
    private var handler: Handler

    // 自定义消息类型
    private val MY_MESSAGE_TYPE = 1

    // 在构造函数中初始化 Handler
    init {
        start() // 启动线程
        handler = object : Handler(looper) {
            override fun handleMessage(msg: Message) {
                // 在工作线程中处理消息
                when (msg.what) {
                    MY_MESSAGE_TYPE -> {
                        val data = msg.obj as String
                        // 处理自定义消息
                        Log.d("zgq","Received message: $data")
                    }
                }
            }
        }
    }

    // 提供公共方法，用于向工作线程发送消息
    fun sendMessageToWorkerThread(data: String) {
        val message = handler.obtainMessage(MY_MESSAGE_TYPE, data)
        handler.sendMessage(message)
    }
}


