package com.example.demo01.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo01.R
import com.example.demo01.adapter.MyAdapter

class BActivity : AppCompatActivity() {
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

    override fun onResume() {
        super.onResume()
        Log.d("zgq","onResume - b")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("zgq","onDestroy - b")
    }

}