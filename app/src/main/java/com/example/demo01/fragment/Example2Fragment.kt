package com.example.demo01.fragment

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.viewModelScope
import com.example.demo01.R
import com.example.demo01.viewmodel.FragmentShareDataViewModel

class Example2Fragment: Fragment(R.layout.example2_fragment) {
    private val viewModel: FragmentShareDataViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("zgq", viewModel.testData.value.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val result = "result"
        // Use the Kotlin extension in the fragment-ktx artifact.
        setFragmentResult("requestKey", bundleOf("bundleKey" to result))
    }
}