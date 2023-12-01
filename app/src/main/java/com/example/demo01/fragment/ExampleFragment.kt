package com.example.demo01.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.demo01.R
import com.example.demo01.viewmodel.FragmentShareDataViewModel

class ExampleFragment: Fragment(R.layout.example_fragment) {

    private val viewModel: FragmentShareDataViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("zgq", arguments?.getString("key1").toString())
        viewModel.testData.value = "hello"
    }
}