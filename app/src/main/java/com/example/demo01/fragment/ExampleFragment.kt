package com.example.demo01.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.example.demo01.R
import com.example.demo01.viewmodel.FragmentShareDataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ExampleFragment: Fragment(R.layout.example_fragment) {

    private val viewModel: FragmentShareDataViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("zgq", arguments?.getString("key1").toString())
        viewModel.testData.value = "hello"

        lifecycleScope.launch {
            val deferred = async(Dispatchers.IO) {
                // 在 IO 线程中执行耗时操作
                //fetchData()
            }

            // 等待异步操作完成，并获取结果
            val result = deferred.await()

            // 在主线程中更新 UI
            //textView.text = result
        }
    }
}