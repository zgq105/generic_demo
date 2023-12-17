package com.example.demo01.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.testData.value = "hello world"
        viewModel.testData.observe(viewLifecycleOwner) {
            Log.d("zgq", "testData:$it")
        }
    }
}