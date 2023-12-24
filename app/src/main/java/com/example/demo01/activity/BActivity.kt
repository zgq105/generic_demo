package com.example.demo01.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo01.R
import com.example.demo01.adapter.MyAdapter
import java.util.UUID

class BActivity : AppCompatActivity() {

    class MyHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.d("zgq", "handleMessage：${msg.obj}")
        }
    }

    private val myHandler = MyHandler()

    override fun onResume() {
        super.onResume()
        Log.d("zgq","onResume - b")
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        Log.d("zgq","onCreate - b")

        // Sample data
        val itemList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create and set the adapter
        val adapter = MyAdapter(itemList)
        recyclerView.adapter = adapter
        findViewById<Button>(R.id.btn_test).setOnClickListener {
            val message = Message.obtain().apply {
                isAsynchronous = true
                obj = "异步消息"
            }
            val message2 = Message.obtain().apply {
                isAsynchronous = false
                obj = "同步消息"
            }
            myHandler.sendMessage(message2)
            myHandler.sendMessage(message)
        }
    }




    override fun onStart() {
        super.onStart()
        Log.d("zgq","onStart - b")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("zgq","onRestart - b")
    }

    override fun onPause() {
        super.onPause()
        Log.d("zgq","onPause - b")
    }

    override fun onStop() {
        super.onStop()
        Log.d("zgq","onStop - b")
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.d("zgq","onDestroy - b")
    }

}